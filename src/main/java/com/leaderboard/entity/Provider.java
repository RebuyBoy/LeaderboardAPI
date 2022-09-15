package com.leaderboard.entity;

public enum Provider {

    GGNETWORK("GG", "GG network"),
    WINAMAX("WM", "Winamax");

    private final String code;
    private final String description;

    Provider(String code, String name) {
        this.code = code;
        this.description = name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

}
