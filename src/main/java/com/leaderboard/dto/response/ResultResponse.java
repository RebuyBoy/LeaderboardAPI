package com.leaderboard.dto.response;

import com.leaderboard.dto.AggregatedResult;

import java.util.List;

public class ResultResponse {
    private String provider;
    private String currency;

    private List<AggregatedResult> aggregatedResults;


    public ResultResponse(String provider, String currency, List<AggregatedResult> aggregatedResults) {
        this.provider = provider;
        this.currency = currency;
        this.aggregatedResults = aggregatedResults;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "provider='" + provider + '\'' +
                ", currency='" + currency + '\'' +
                ", aggregatedResults=" + aggregatedResults +
                '}';
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<AggregatedResult> getAggregatedResults() {
        return aggregatedResults;
    }

    public void setAggregatedResults(List<AggregatedResult> aggregatedResults) {
        this.aggregatedResults = aggregatedResults;
    }
}
