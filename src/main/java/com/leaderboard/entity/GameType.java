package com.leaderboard.entity;

public enum GameType {
//TODO remove code and converters
    SHORT_DECK("SD","short-deck"),
    OMAHA("OM", "omaha");
//    SPIN_GOLD("SG", "spin-gold"),
//    HOLDEM("HM","holdem");

    private final String code;
    private final String description;

    GameType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
