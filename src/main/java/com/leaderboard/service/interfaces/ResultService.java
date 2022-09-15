package com.leaderboard.service.interfaces;

import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;

import java.time.LocalDate;
import java.util.List;

public interface ResultService {

    List<Result> getByDateFrom(LocalDate from);

    List<Result> getByDateBetween(LocalDate from, LocalDate to);

    List<Result> getAllByStake(Provider provider, GameType gameType, Stake stake);

    List<Result> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, Stake stake);

    void saveIfNotExists(Result result);

}
