package com.leaderboard.service.interfaces;

import com.leaderboard.entity.GameType;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {

    void runDailyDataFlow(List<LocalDate> dates, GameType gameType);

    void runDailyDataFlow();

    void runDailyDataFlow(LocalDate date,GameType gameType);

}
