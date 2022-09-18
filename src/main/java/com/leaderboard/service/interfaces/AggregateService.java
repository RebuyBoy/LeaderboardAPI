package com.leaderboard.service.interfaces;

import com.leaderboard.dto.api.response.ResultResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Stake;

import java.time.LocalDate;

public interface AggregateService {

    ResultResponse getAllByStake(Provider provider, GameType gameType, Stake stake);

    ResultResponse getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, Stake stake);

}
