package com.leaderboard.service;

import com.leaderboard.converters.ResultDtoResultConverter;
import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.dto.response.GroupsResponseDTO;
import com.leaderboard.dto.response.SetsDTO;
import com.leaderboard.dto.response.SubsetsDTO;
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
import static com.leaderboard.constants.Constants.PromoUrlFormat;
import static com.leaderboard.constants.Constants.stakesUrlLimit;

@Service
public class GGClientService {

    private final RequestService requestService;
    private final ResultDtoResultConverter converter;
    private final ResultService resultService;
    private GroupsResponseDTO groupsResponseDTO;


    public GGClientService(RequestService requestService
            , ResultDtoResultConverter converter
            , ResultService resultService) {
        this.requestService = requestService;
        this.converter = converter;
        this.resultService = resultService;
    }


    public void getMonthlyData() {
        String responseWithGroupId = requestService.mainPromoRequest(GGN_SHORT_DECK_PROMO_URL);
        String groupId = findGroupIdFromResponse(responseWithGroupId);
        String url = GGN_GROUP_ID_REQUEST_BASE + groupId;
        this.groupsResponseDTO = requestService.groupIdRequest(url);
    }

    public void getDailyData() {
        if (groupsResponseDTO == null) {
            getMonthlyData();
        }
        LocalDate today = ZonedDateTime.now(ZoneId.of("UTC-8")).toLocalDate();
        SetsDTO sets = findSetByDate(groupsResponseDTO, today);
        for (SubsetsDTO subset : sets.getSubsets()) {
            String stake = subset.getStake();
            int promotionId = subset.getPromotionId();
            String url = generateUrl(formatStake(stake), promotionId);
            List<GGResultDTO> resultDTOS = requestService.promotionIdRequest(url);
            resultDTOS.stream()
                    .map(resultDTO -> converter.dtoToResult(resultDTO, today, stake))
                    .peek(System.out::println)
                    .forEach(resultService::save);

            //convert GPlayerResultDTO -> PlayerResult entity
            //save to DB
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

//    private List<String> generatePromotionUrls(List<SubsetsDTO> sets) {
//        return sets
//                .stream()
//                .filter(subsetsDTO -> suitableStakes.contains(subsetsDTO.getStake()))
//                .map(this::generateUrl)
//                .collect(Collectors.toMap(s));
//    }

    private String generateUrl(String stake, int promotionId) {
        return String.format(PromoUrlFormat, promotionId, stake);
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    private String formatStake(String stake) {
        if (stakesUrlLimit.containsKey(stake)) {
            return stakesUrlLimit.get(stake);
        }
        throw new IllegalArgumentException("wrong stake: " + stake);
    }
}
