package com.leaderboard.entity;

public enum GameType {
    SHORT_DECK("short-deck"),
    OMAHA("omaha");
//    SPIN_GOLD("SG", "spin-gold"),
//    HOLDEM("HM","holdem");

    private final String description;

    GameType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
