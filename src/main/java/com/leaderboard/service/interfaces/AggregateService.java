package com.leaderboard.service.interfaces;

import com.leaderboard.dto.AggregatedResultDTO;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;

import java.time.LocalDate;
import java.util.List;

public interface AggregateService {

    List<AggregatedResultDTO> getAll(Provider provider, GameType gameType);
    List<AggregatedResultDTO> getAllByStake(Provider provider, GameType gameType, String stakeEquivalent);
    List<AggregatedResultDTO> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, String stakeEquivalent);

}
