package com.leaderboard.dto.api.response;

public class StakeResponse {

    private String value;

    public StakeResponse(String value) {
        this.value = value;
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
                "value='" + value + '\'' +
                '}';
    }

}
