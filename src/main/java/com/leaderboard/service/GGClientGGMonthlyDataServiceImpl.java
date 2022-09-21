package com.leaderboard.service;

import com.leaderboard.dto.client.gg.GroupsResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.GroupId;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.service.interfaces.GGMonthlyDataService;
import com.leaderboard.service.interfaces.GGRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GGClientGGMonthlyDataServiceImpl implements GGMonthlyDataService {

    private static final Logger LOG = LoggerFactory.getLogger(GGClientGGMonthlyDataServiceImpl.class);
    private static final String GROUP_ID_REGEX = "groupId=(\\d+)";
    private static final String GGN_BASE_PROMO_FORMAT = "https://play.pokerok900.com/promotions/promo-%s";
    private static final String GGN_GROUP_ID_REQUEST_FORMAT = "https://pml.good-game-network.com/lapi/leaderboard/groups/%s";


    private final GGRequestService requestService;
    private final GGGroupIdService groupIdService;


    public GGClientGGMonthlyDataServiceImpl(GGRequestService requestService,
                                            GGGroupIdService groupIdService) {

        this.requestService = requestService;
        this.groupIdService = groupIdService;
    }

    @Override
    @Cacheable("groupResponse")
    public GroupsResponse parseMonthlyData(GameType gameType) {
        LOG.info("Started parsing monthly data by game type: {}", gameType);
        GroupsResponse groupsResponse = null;
        String groupUrl = generateMainPromoUrl(gameType);
        try {
            LOG.debug("Update monthly data started with url: {}", groupUrl);
            String groupId = findGroupIdFromResponse(requestService.getHTMLBody(groupUrl));
            String urlWithGroupId = generateGroupIdUrl(groupId);
            groupIdService.saveIfNotExists(buildGroupId(gameType, groupId));
            groupsResponse = requestService.groupIdRequest(urlWithGroupId);
        } catch (Exception e) {
            LOG.error("Parsing monthly data failed ", e);
        }
        return groupsResponse;
    }

    @Override
    @CacheEvict("groupResponse")
    public void deleteGroupResponseCache() {
    }

    private String generateMainPromoUrl(GameType gameType) {
        return GGN_BASE_PROMO_FORMAT.formatted(gameType.getDescription());
    }

    private String findGroupIdFromResponse(String response) {
        Matcher matcher = Pattern.compile(GROUP_ID_REGEX).matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new NoResultException("Group id not found");
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

}
