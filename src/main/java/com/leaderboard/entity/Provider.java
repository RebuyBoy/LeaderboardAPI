package com.leaderboard.entity;

public enum Provider {

    GGNETWORK("GG", "GG network", "USD"),
    WINAMAX("WM", "Winamax", "EUR");

    private final String code;
    private final String description;
    private final String currency;

    Provider(String code, String name, String currency) {
        this.code = code;
        this.description = name;
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public String getCurrency() {
        return currency;
    }

}
