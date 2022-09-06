package com.leaderboard.schedule;

import com.leaderboard.service.GGClientService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.leaderboard.constants.Constants.EVERY_DAY_PLUS_3_MINUTE_AFTER_MIDNIGHT_CRON;
import static com.leaderboard.constants.Constants.GMT_8;

@Service
public class GGScheduler {
    private final GGClientService clientService;

    public GGScheduler(GGClientService clientService) {
        this.clientService = clientService;
    }

    @Scheduled(cron = EVERY_DAY_PLUS_3_MINUTE_AFTER_MIDNIGHT_CRON, zone = GMT_8)
    public void dailyPromotionData() {
        clientService.getMonthlyData();
    }
}


