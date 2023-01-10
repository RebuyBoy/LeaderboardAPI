package com.leaderboard.service.interfaces;

import com.leaderboard.dto.api.ResultTelegram;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Stake;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {

    void runDailyDataFlow(List<LocalDate> dates, GameType gameType);

    void runDailyDataFlow();

    void runDailyDataFlow(LocalDate date,GameType gameType);

    List<ResultTelegram> runDailyDataFlow(Stake stake, GameType gameType);
}
