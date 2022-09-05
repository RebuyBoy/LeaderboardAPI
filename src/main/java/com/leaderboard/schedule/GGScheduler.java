package com.leaderboard.schedule;

import com.leaderboard.service.GGClientService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GGScheduler {
    private static final String EVERY_DAY_PLUS_3_MINUTE_AFTER_MIDNIGHT_CRON = "3 0 * * * ?";
    private static final String GMT_8 = "GMT-8";
    private final GGClientService clientService;

    public GGScheduler(GGClientService clientService) {
        this.clientService = clientService;
    }

    @Scheduled(cron = EVERY_DAY_PLUS_3_MINUTE_AFTER_MIDNIGHT_CRON, zone = GMT_8)
    public void dailyPromotionData() {
        clientService.getMonthlyData();
    }
}


