package com.leaderboard.service.interfaces;

import com.leaderboard.dto.client.gg.GroupsResponse;
import com.leaderboard.entity.GameType;

public interface GGMonthlyDataService {

    GroupsResponse parseMonthlyData(GameType gameType);

    void deleteGroupResponseCache();

}
