package com.leaderbord.service;


import com.leaderbord.entity.PlayerResult;

import java.util.List;

public interface LeaderboardService {
    void addAll(List<PlayerResult> players);

    void saveAll(List<PlayerResult> collect);
}
