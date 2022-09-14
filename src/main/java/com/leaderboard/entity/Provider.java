package com.leaderboard.entity;

public enum Provider {
    GGNETWORK("GG", "USD"), WINAMAX("WM", "EUR");

    private final String code;
    private final String currency;

    Provider(String code, String currency) {
        this.code = code;
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public String getCurrency() {
        return currency;
    }
}
