package com.leaderboard.service.interfaces;

import com.leaderboard.dto.api.AggregatedResult;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Stake;

import java.time.LocalDate;
import java.util.List;

public interface AggregateService {

    List<AggregatedResult> getAllByStake(Provider provider, GameType gameType, Stake stake);

    List<AggregatedResult> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, Stake stake);

}
