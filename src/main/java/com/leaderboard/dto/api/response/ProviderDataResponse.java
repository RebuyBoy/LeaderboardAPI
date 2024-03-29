package com.leaderboard.dto.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class ProviderDataResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastUpdate;
    private List<ProviderData> providersData;

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public ProviderDataResponse(LocalDate lastUpdate, List<ProviderData> providersData) {
        this.lastUpdate = lastUpdate;
        this.providersData = providersData;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<ProviderData> getProvidersData() {
        return providersData;
    }

    public void setProvidersData(List<ProviderData> providersData) {
        this.providersData = providersData;
    }

    @Override
    public String toString() {
        return "ProviderDataResponse{" +
                "lastUpdate=" + lastUpdate +
                ", providersData=" + providersData +
                '}';
    }

}
