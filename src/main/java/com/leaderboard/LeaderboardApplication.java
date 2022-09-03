package com.leaderboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LeaderboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeaderboardApplication.class, args);
    }
}
