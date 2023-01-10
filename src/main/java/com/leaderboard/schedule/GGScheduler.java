package com.leaderboard.schedule;

import com.leaderboard.service.interfaces.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.leaderboard.constants.Constants.GMT_MINUS_8;

@Service
public class GGScheduler {
    public static final String EVERY_DAY_PLUS_3_MINUTES_AFTER_MIDNIGHT_CRON = "0 3 0 * * *";
    private static final Logger LOG = LoggerFactory.getLogger(GGScheduler.class);
    private final ClientService clientService;

    public GGScheduler(ClientService clientService) {
        this.clientService = clientService;
    }

    @Scheduled(cron = EVERY_DAY_PLUS_3_MINUTES_AFTER_MIDNIGHT_CRON, zone = GMT_MINUS_8)
    public void dailyPromotionData() {
        LOG.info("scheduler starts collecting data");
        clientService.runDailyDataFlow();
    }
    //TODO random start time
    // check current cron
    // delay between requests?

}

//    Cron
// ┌───────────── second (0-59)
// │ ┌───────────── minute (0 - 59)
// │ │ ┌───────────── hour (0 - 23)
// │ │ │ ┌───────────── day of the month (1 - 31)
// │ │ │ │ ┌───────────── month (1 - 12) (or JAN-DEC)
// │ │ │ │ │ ┌───────────── day of the week (0 - 7)
// │ │ │ │ │ │          (or MON-SUN -- 0 or 7 is Sunday)
// │ │ │ │ │ │
// * * * * * *


