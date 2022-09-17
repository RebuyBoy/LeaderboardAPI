package com.leaderboard.dto.api.response;

public class ProviderResponse {

    private String code;
    private String name;
    private String currency;

    public ProviderResponse(String code, String name, String currency) {
        this.code = code;
        this.name = name;
        this.currency = currency;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ProviderResponse{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }

}
