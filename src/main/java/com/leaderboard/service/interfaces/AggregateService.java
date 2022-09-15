package com.leaderboard.service.interfaces;

import com.leaderboard.dto.AggregatedResultDTO;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Stake;

import java.time.LocalDate;
import java.util.List;

public interface AggregateService {
    List<AggregatedResultDTO> getAllByStake(Provider provider, GameType gameType, Stake stake);
    List<AggregatedResultDTO> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, Stake stake);

}
