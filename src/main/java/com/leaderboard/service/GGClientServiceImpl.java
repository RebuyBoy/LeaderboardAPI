package com.leaderboard.service;

import com.leaderboard.converters.GameTypeConverter;
import com.leaderboard.converters.ResultResponseConverter;
import com.leaderboard.dto.client.gg.GGResultResponse;
import com.leaderboard.dto.client.gg.GroupsResponse;
import com.leaderboard.dto.client.gg.SetsResponse;
import com.leaderboard.dto.client.gg.SubsetsResponse;
import com.leaderboard.entity.GroupId;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.service.interfaces.ClientService;
import com.leaderboard.service.interfaces.GGRequestService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.leaderboard.constants.Constants.GGN_GROUP_ID_REQUEST_BASE;
import static com.leaderboard.constants.Constants.GGN_SHORT_DECK_PROMO_URL;
import static com.leaderboard.constants.Constants.GMT_MINUS_8;
import static com.leaderboard.constants.Constants.PROMO_URL_FORMAT;
import static com.leaderboard.constants.Constants.STAKES_TO_PART_OF_URL;
import static com.leaderboard.constants.Constants.SUITABLE_STAKES;

@Service
public class GGClientServiceImpl implements ClientService {

    private final GGRequestService ggRequestService;
    private final ResultResponseConverter resultConverter;
    private final ResultService resultService;
    private final GGGroupIdService groupIdService;
    private final GameTypeConverter gameTypeConverter;
    private GroupsResponse groupsResponse;

    public GGClientServiceImpl(GGRequestService ggRequestService,
                               ResultResponseConverter converter,
                               ResultService resultService,
                               GGGroupIdService groupIdService,
                               GameTypeConverter gameTypeConverter) {

        this.ggRequestService = ggRequestService;
        this.resultConverter = converter;
        this.resultService = resultService;
        this.groupIdService = groupIdService;
        this.gameTypeConverter = gameTypeConverter;
    }

    @Override
    public void runDailyDataFlow(List<LocalDate> dates) {
        for (LocalDate date : dates) {
            runDailyDataFlow(date);
        }
    }

    @Override
    public void runDailyDataFlow() {
        runDailyDataFlow(getTargetDay());
    }

    private LocalDate getTargetDay() {
        return ZonedDateTime
                .now(ZoneId.of(GMT_MINUS_8))
                .minusDays(1)
                .toLocalDate();
    }

    @Override
    public void runDailyDataFlow(LocalDate date) {
        if (Objects.isNull(groupsResponse) || isOutdatedMonth(groupsResponse, date)) {
            updateMonthlyData();
        }
        try {
            SetsResponse sets = findSetByDate(groupsResponse, date);
            for (SubsetsResponse subset : sets.getSubsets()) {
                String stake = subset.getStake();
                if (SUITABLE_STAKES.contains(stake)) {
                    handleData(date, subset, stake);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isOutdatedMonth(GroupsResponse groupsResponse, LocalDate date) {
        return groupsResponse.getStartedAt().getMonthValue() != (date.getMonthValue());
    }

    private void updateMonthlyData() {
        try {
            String responseWithGroupId = ggRequestService.getHTMLBody(GGN_SHORT_DECK_PROMO_URL);
            String groupIdStr = findGroupIdFromResponse(responseWithGroupId);
            this.groupsResponse = ggRequestService.groupIdRequest(getGroupIdRequestUrl(groupIdStr));
            GroupId groupId = new GroupId.Builder()
                    .promotionGroupId(groupIdStr)
                    .date(getCurrentMonthYear())
                    .gameType(gameTypeConverter.convertToEntityAttributeByName(groupsResponse.getGameTypes()[0]))
                    .build();
            groupIdService.saveIfNotExists(groupId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleData(LocalDate date, SubsetsResponse subset, String stake) {
        List<GGResultResponse> resultDTOS = getGGResultDTOS(subset.getPromotionId(), stake);
        saveResults(date, stake, resultDTOS);
    }

    private void saveResults(LocalDate date, String stake, List<GGResultResponse> resultDTOS) {
        String gameType = groupsResponse.getGameTypes()[0];
        String provider = groupsResponse.getHost();
        resultDTOS.stream()
                .map(resultDTO -> resultConverter.convert(resultDTO, date, stake, gameType, provider))
                .forEach(resultService::saveIfNotExists);
    }

    private List<GGResultResponse> getGGResultDTOS(int promotionId, String stake) {
        String url = generateUrl(formatStake(stake), promotionId);
        return ggRequestService.promotionIdRequest(url);
    }


    private SetsResponse findSetByDate(GroupsResponse groupsResponse, LocalDate date) {
        return groupsResponse.getSets().stream()
                .filter(set -> set.getDate().equals(date))
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

    private String formatStake(String stake) {
        if (STAKES_TO_PART_OF_URL.containsKey(stake)) {
            return STAKES_TO_PART_OF_URL.get(stake);
        }
        throw new IllegalArgumentException("wrong stake: " + stake);
    }

    private LocalDate getCurrentMonthYear() {
        return LocalDate.now().withDayOfMonth(1);
    }

    private String getGroupIdRequestUrl(String groupId) {
        return String.format("%s%s", GGN_GROUP_ID_REQUEST_BASE, groupId);
    }

}
