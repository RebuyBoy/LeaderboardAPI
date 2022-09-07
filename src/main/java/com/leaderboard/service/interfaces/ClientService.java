package com.leaderboard.service.interfaces;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {
    void runDailyDataFlow(List<LocalDate> dates);

    void runDailyDataFlow();

    void runDailyDataFlow(LocalDate date);
}
