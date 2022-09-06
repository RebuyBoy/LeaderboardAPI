package com.leaderboard.service;

import com.leaderboard.converters.ResultDtoResultConverter;
import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.dto.response.GroupsResponseDTO;
import com.leaderboard.dto.response.SetsDTO;
import com.leaderboard.dto.response.SubsetsDTO;
import com.leaderboard.entity.GroupId;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.service.interfaces.RequestService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.leaderboard.constants.Constants.GGN_GROUP_ID_REQUEST_BASE;
import static com.leaderboard.constants.Constants.GGN_SHORT_DECK_PROMO_URL;
import static com.leaderboard.constants.Constants.GMT_8;
import static com.leaderboard.constants.Constants.PROMO_URL_FORMAT;
import static com.leaderboard.constants.Constants.STAKES_TO_PART_OF_URL;
import static com.leaderboard.constants.Constants.SUITABLE_STAKES;

@Service
public class GGClientService {

    private final RequestService requestService;
    private final ResultDtoResultConverter converter;
    private final ResultService resultService;
    private final GGGroupIdService groupIdService;
    private GroupsResponseDTO groupsResponseDTO;


    public GGClientService(RequestService requestService
            , ResultDtoResultConverter converter
            , ResultService resultService
            , GGGroupIdService groupIdService) {
        this.requestService = requestService;
        this.converter = converter;
        this.resultService = resultService;
        this.groupIdService = groupIdService;
    }


    public void getMonthlyData() {
        try {
            String responseWithGroupId = requestService.mainPromoRequest(GGN_SHORT_DECK_PROMO_URL);
            String groupId = findGroupIdFromResponse(responseWithGroupId);

            LocalDate currentMonthYear = LocalDate.now().withDayOfMonth(1);
            groupIdService.saveIfNotExists(new GroupId(currentMonthYear, groupId));

            String url = GGN_GROUP_ID_REQUEST_BASE + groupId;
            this.groupsResponseDTO = requestService.groupIdRequest(url);
        } catch (Exception e) {
            //logger
            System.out.println(e.getMessage());
        }
    }

    public void getDailyData() {
        LocalDate today = ZonedDateTime.now(ZoneId.of(GMT_8)).toLocalDate();
        getDailyData(today);
    }

    public void getDailyData(LocalDate date) {
        if (groupsResponseDTO == null) {
            getMonthlyData();
        }
        try {
            SetsDTO sets = findSetByDate(groupsResponseDTO, date);
            for (SubsetsDTO subset : sets.getSubsets()) {
                String stake = subset.getStake();
                if (SUITABLE_STAKES.contains(stake)) {
                    int promotionId = subset.getPromotionId();
                    String gameType = groupsResponseDTO.getGameTypes()[0];
                    String url = generateUrl(formatStake(stake), promotionId);
                    List<GGResultDTO> resultDTOS = requestService.promotionIdRequest(url);
                    resultDTOS.stream()
                            .map(resultDTO -> converter.dtoToResult(resultDTO, date, stake, gameType))
                            .forEach(resultService::save);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //logger
        }
    }

    public void getDailyData(List<LocalDate> dates) {
        for (LocalDate date : dates) {
            getDailyData(date);
        }
    }


    private SetsDTO findSetByDate(GroupsResponseDTO groupsResponse, LocalDate date) {
        return groupsResponse.getSets().stream()
                .filter(sets -> parseDate(sets.getDate()).equals(date))
                .findFirst()
                .orElseThrow(() -> new NoResultException("Promotions not found by date: " + date));
    }

    private String findGroupIdFromResponse(String response) {
        Matcher matcher = Pattern.compile("groupId=(\\d+)").matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new NoResultException("Group id not found");
    }

    private String generateUrl(String stake, int promotionId) {
        return String.format(PROMO_URL_FORMAT, promotionId, stake);
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    private String formatStake(String stake) {
        if (STAKES_TO_PART_OF_URL.containsKey(stake)) {
            return STAKES_TO_PART_OF_URL.get(stake);
        }
        throw new IllegalArgumentException("wrong stake: " + stake);
    }
}
