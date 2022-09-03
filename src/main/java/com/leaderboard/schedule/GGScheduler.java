package com.leaderboard.schedule;

import com.leaderboard.service.GGClientService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GGScheduler {
    private final GGClientService clientService;

    public GGScheduler(GGClientService clientService) {
        this.clientService = clientService;
    }

//    @Scheduled(cron = "3 0 * * * ?", zone = "UTC-8")
//    public void dailyPromotionData() {
//        clientService.getMonthlyData();
//    }

//    @Scheduled(cron = "0 15 1 * ? *", zone = "UTC-8")
//    public void monthlyGroupData() {
//        clientService.getDailyData();
//    }

//    <minute> <hour> <day-of-month> <month> <day-of-week> <command>
    //LOGGER parsed

}

