package com.leaderboard.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableJpaRepositories(basePackages = "com.leaderboard.repository")
@EntityScan(basePackages = {"com.leaderboard.entity", "com.leaderboard.converters"})
@ComponentScan(basePackages = "com.leaderboard")
@EnableScheduling
public class LeaderboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaderboardApplication.class, args);
    }

}
