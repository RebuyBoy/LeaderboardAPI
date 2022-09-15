package com.leaderboard.entity;

public enum Provider {
    GGNETWORK("GG", "GG network"),
    WINAMAX("WM", "Winamax");

    private final String code;
    private final String name;

    Provider(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
