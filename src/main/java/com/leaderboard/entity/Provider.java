package com.leaderboard.entity;

public enum Provider {

    GGNETWORK("GG network", "USD"),
    WINAMAX("Winamax", "EUR");

    private final String description;
    private final String currency;

    Provider(String name, String currency) {
        this.description = name;
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrency() {
        return currency;
    }

}
