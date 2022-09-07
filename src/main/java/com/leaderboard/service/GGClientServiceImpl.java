package com.leaderboard.service;

import com.leaderboard.converters.ResultDtoResultConverter;
import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.dto.response.GroupsResponse;
import com.leaderboard.dto.response.SetsResponse;
import com.leaderboard.dto.response.SubsetsResponse;
import com.leaderboard.entity.GroupId;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.service.interfaces.ClientService;
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

import static com.leaderboard.constants.Constants.*;

@Service
public class GGClientServiceImpl implements ClientService {

    private final RequestService requestService;
    private final ResultDtoResultConverter converter;
    private final ResultService resultService;
    private final GGGroupIdService groupIdService;
    private GroupsResponse groupsResponseDTO;


    public GGClientServiceImpl(RequestService requestService
            , ResultDtoResultConverter converter
            , ResultService resultService
            , GGGroupIdService groupIdService) {
        this.requestService = requestService;
        this.converter = converter;
        this.resultService = resultService;
        this.groupIdService = groupIdService;
    }


    @Override
    public void runDailyDataFlow(List<LocalDate> dates) {
        for (LocalDate date : dates) {
            runDailyDataFlow(date);
        }
    }

    @Override
    public void runDailyDataFlow() {
        LocalDate today = ZonedDateTime.now(ZoneId.of(GMT_8)).toLocalDate();
        runDailyDataFlow(today);
    }

    @Override
    public void runDailyDataFlow(LocalDate date) {
        // TODO: 07.09.2022  compare month and update
        if (groupsResponseDTO == null) {
            updateMonthlyData();
        }
        try {
            SetsResponse sets = findSetByDate(groupsResponseDTO, date);
            for (SubsetsResponse subset : sets.getSubsets()) {
                String stake = subset.getStake();
                if (SUITABLE_STAKES.contains(stake))
                    handleData(date, subset, stake);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //logger
        }
    }

    private void updateMonthlyData() {
        try {
            String responseWithGroupId = requestService.getHTMLBody(GGN_SHORT_DECK_PROMO_URL);
            String groupId = findGroupIdFromResponse(responseWithGroupId);
            groupIdService.saveIfNotExists(new GroupId(getCurrentMonthYear(), groupId));
            this.groupsResponseDTO = requestService.groupIdRequest(getGroupIdRequestUrl(groupId));
        } catch (Exception e) {
            //logger
            System.out.println(e.getMessage());
        }
    }

    private void handleData(LocalDate date, SubsetsResponse subset, String stake) {
        List<GGResultDTO> resultDTOS = getGGResultDTOS(subset.getPromotionId(), stake);
        saveResultData(date, stake, resultDTOS);
    }

    private void saveResultData(LocalDate date, String stake, List<GGResultDTO> resultDTOS) {
        String gameType = groupsResponseDTO.getGameTypes()[0];
        resultDTOS.stream()
                .map(resultDTO -> converter.dtoToResult(resultDTO, date, stake, gameType))
                .forEach(resultService::save);
    }

    private List<GGResultDTO> getGGResultDTOS(int promotionId, String stake) {
        String url = generateUrl(formatStake(stake), promotionId);
        return requestService.promotionIdRequest(url);
    }


    private SetsResponse findSetByDate(GroupsResponse groupsResponse, LocalDate date) {
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

    // TODO: 07.09.2022 into UTIL
    private LocalDate getCurrentMonthYear() {
        return LocalDate.now().withDayOfMonth(1);
    }

    private String getGroupIdRequestUrl(String groupId) {
        return String.format("%s%s", GGN_GROUP_ID_REQUEST_BASE, groupId);
    }

}
