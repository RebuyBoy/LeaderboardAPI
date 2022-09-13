package com.leaderboard.entity;

public enum Provider {
    GGNETWORK("GG"), WINAMAX("WM");

    private final String code;

    Provider(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
