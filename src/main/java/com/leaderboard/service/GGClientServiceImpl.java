package com.leaderboard.service;

import com.leaderboard.converters.ResultResponseConverter;
import com.leaderboard.dto.client.gg.GGResultResponse;
import com.leaderboard.dto.client.gg.GroupsResponse;
import com.leaderboard.dto.client.gg.SetsResponse;
import com.leaderboard.dto.client.gg.SubsetsResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.GroupId;
import com.leaderboard.entity.Provider;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.service.interfaces.ClientService;
import com.leaderboard.service.interfaces.GGRequestService;
import com.leaderboard.service.interfaces.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.leaderboard.constants.Constants.GAME_TYPES_PART_OF_URL;
import static com.leaderboard.constants.Constants.GAME_TYPES_SUITABLE_STAKES;
import static com.leaderboard.constants.Constants.GMT_MINUS_8;

@Service
public class GGClientServiceImpl implements ClientService {

    private static final String GROUP_ID_REGEX = "groupId=(\\d+)";
    private static final String GGN_GROUP_ID_REQUEST_FORMAT = "https://pml.good-game-network.com/lapi/leaderboard/groups/%s";
    private static final String GGN_BASE_PROMO_FORMAT = "https://play.pokerok900.com/promotions/promo-%s";
    private static final String PROMO_URL_FORMAT = "https://pml.good-game-network.com/lapi/leaderboard/%s/?status=PENDING&status=OPTED_IN&status=COMPLETED&status=EXPIRED&limit=%s&hasSummary=true&hasSummaryPaidPrizes=true&hasSummaryPrizeItem=true";
    private static final Logger LOG = LoggerFactory.getLogger(GGClientServiceImpl.class);

    private final GGRequestService ggRequestService;
    private final ResultResponseConverter resultConverter;
    private final ResultService resultService;
    private final GGGroupIdService groupIdService;
    private final Map<GameType, GroupsResponse> gameTypeGroupsResponse = new EnumMap<>(GameType.class);

    public GGClientServiceImpl(GGRequestService ggRequestService,
                               ResultResponseConverter converter,
                               ResultService resultService,
                               GGGroupIdService groupIdService) {

        this.ggRequestService = ggRequestService;
        this.resultConverter = converter;
        this.resultService = resultService;
        this.groupIdService = groupIdService;
    }

    @Override
    public void runDailyDataFlow() {
        List.of(GameType.values())
                .forEach(gameType -> runDailyDataFlow(getTargetDay(), gameType));
    }


    @Override
    public void runDailyDataFlow(LocalDate date, GameType gameType) {
        GroupsResponse groupsResponse = gameTypeGroupsResponse.get(gameType);
        if (Objects.isNull(groupsResponse) || isOutdatedMonth(groupsResponse, date)) {
            updateMonthlyData(gameType);
            groupsResponse = gameTypeGroupsResponse.get(gameType);
        }
        try {
            LOG.debug("Parsing data started by date: {} and gameType: {}", date, gameType);
            SetsResponse sets = findSetByDate(groupsResponse, date);
            for (SubsetsResponse subset : sets.getSubsets()) {
                String stake = subset.getStake();
                if (GAME_TYPES_SUITABLE_STAKES.get(gameType).contains(stake)) {
                    handleData(date, subset, stake, gameType);
                }
            }
        } catch (Exception e) {
            LOG.error("Parse data failed ", e);
        }
    }

    @Override
    public void runDailyDataFlow(List<LocalDate> dates, GameType gameType) {
        for (LocalDate date : dates) {
            runDailyDataFlow(date, gameType);
        }
    }

    //TODO return GroupResponse and @Cacheable   check by gameType??
    private void updateMonthlyData(GameType gameType) {
        String groupUrl = generateMainPromoUrl(gameType);
        try {
            LOG.debug("Update monthly data started with url: {}", groupUrl);
            String groupId = findGroupIdFromResponse(ggRequestService.getHTMLBody(groupUrl));
            String urlWithGroupId = generateGroupIdUrl(groupId);
            gameTypeGroupsResponse.put(gameType, ggRequestService.groupIdRequest(urlWithGroupId));

            groupIdService.saveIfNotExists(buildGroupId(gameType, groupId));
        } catch (Exception e) {
            LOG.error("Update monthly data failed ", e);
        }
    }

    private void handleData(LocalDate date, SubsetsResponse subset, String stake, GameType gameType) {
        List<GGResultResponse> resultDTOS = getGGResultDTOS(gameType,subset.getPromotionId(), stake);
        saveResults(date, stake, resultDTOS, gameType);
    }

    private void saveResults(LocalDate date, String stake, List<GGResultResponse> resultDTOS, GameType gameType) {
        resultDTOS.stream()
                .map(resultDTO -> resultConverter.convert(resultDTO, date, stake, gameType, Provider.GGNETWORK))
                .forEach(resultService::saveIfNotExists);
    }

    private boolean isOutdatedMonth(GroupsResponse groupsResponse, LocalDate date) {
        return groupsResponse.getStartedAt().getMonthValue() != (date.getMonthValue());
    }

    private List<GGResultResponse> getGGResultDTOS(GameType gameType, int promotionId, String stake) {
        String url = generatePromotionUrl(getLimitValueByStake(gameType,stake), promotionId);
        return ggRequestService.promotionIdRequest(url);
    }

    private SetsResponse findSetByDate(GroupsResponse groupsResponse, LocalDate date) {
        return groupsResponse.getSets().stream()
                .filter(set -> set.getDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new NoResultException("Promotions not found by date: " + date));
    }

    private String findGroupIdFromResponse(String response) {
        Matcher matcher = Pattern.compile(GROUP_ID_REGEX).matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new NoResultException("Group id not found");
    }

    private String generatePromotionUrl(String stake, int promotionId) {
        return PROMO_URL_FORMAT.formatted(promotionId, stake);
    }

    private String generateMainPromoUrl(GameType gameType) {
        return GGN_BASE_PROMO_FORMAT.formatted(gameType.getDescription());
    }

    private String getLimitValueByStake(GameType gameType, String stake) {
        Map<String, String> stakesToPartUrl = GAME_TYPES_PART_OF_URL.get(gameType);
        if (stakesToPartUrl.containsKey(stake)) {
            return stakesToPartUrl.get(stake);
        }
        throw new IllegalArgumentException("wrong stake: " + stake);
    }

    private GroupId buildGroupId(GameType gameType, String groupIdStr) {
        return new GroupId.Builder()
                .promotionGroupId(groupIdStr)
                .date(getFirstDayOfCurrentMonthYear())
                .gameType(gameType)
                .build();
    }

    private LocalDate getFirstDayOfCurrentMonthYear() {
        return LocalDate.now().withDayOfMonth(1);
    }

    private String generateGroupIdUrl(String groupId) {
        return GGN_GROUP_ID_REQUEST_FORMAT.formatted(groupId);
    }

    private LocalDate getTargetDay() {
        return ZonedDateTime
                .now(ZoneId.of(GMT_MINUS_8))
                .minusDays(1)
                .toLocalDate();
    }

}
