package com.leaderboard.schedule;

import com.leaderboard.service.GGClientService;
import org.springframework.stereotype.Service;

@Service
public class GGScheduler {
    private final GGClientService clientService;

    public GGScheduler(GGClientService clientService) {
        this.clientService = clientService;
    }

    public void run(){
        clientService.getMonthlyData();
        clientService.getDailyData();
    }

}

