package com.leaderboard.dto.api.response;

public class ProviderResponse {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProviderResponse{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
