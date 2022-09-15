package com.leaderboard.dto.api.response;

import java.time.LocalDate;
import java.util.List;

public class ProviderDataResponse {
    private LocalDate lastUpdate;
    private List<ProviderData> providersData;

    public LocalDate getLastUpdate() {
        return lastUpdate;
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
