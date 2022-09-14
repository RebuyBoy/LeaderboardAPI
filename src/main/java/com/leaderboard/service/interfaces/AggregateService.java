package com.leaderboard.service.interfaces;

import com.leaderboard.dto.AggregatedResult;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;

import java.time.LocalDate;
import java.util.List;

public interface AggregateService {

    List<AggregatedResult> getAll(Provider provider, GameType gameType);

    List<AggregatedResult> getAllByStake(Provider provider, GameType gameType, String stakeEquivalent);

    List<AggregatedResult> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, String stakeEquivalent);

}
