package com.leaderboard.dto.api.response;

public class StakeResponse {
    private String currency;
    private String value;

    public StakeResponse(String currency, String value) {
        this.currency = currency;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StakeResponse{" +
                "currency='" + currency + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
