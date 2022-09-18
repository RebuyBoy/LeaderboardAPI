package com.leaderboard.entity;

public enum GameType {

    SHORT_DECK("SD"),
    OMAHA("OM"),
    SPIN_AND_GOLD("SG"),
    HOLDEM("HM");

    private final String code;

    GameType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
