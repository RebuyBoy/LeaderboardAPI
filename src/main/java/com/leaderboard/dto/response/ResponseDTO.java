package com.leaderboard.dto.response;


public class ResponseDTO {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "data='" + data + '\'' +
                '}';
    }
}
