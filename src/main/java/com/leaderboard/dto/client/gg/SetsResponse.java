package com.leaderboard.dto.client.gg;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SetsResponse {
    @JsonProperty("name")
    private String date;
    //TODO  to localDate
    private List<SubsetsResponse> subsets;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<SubsetsResponse> getSubsets() {
        return subsets;
    }

    public void setSubsets(List<SubsetsResponse> subsets) {
        this.subsets = subsets;
    }

    @Override
    public String toString() {
        return "SetsDTO{" +
                "date='" + date + '\'' +
                ", subsets=" + subsets +
                '}';
    }
}
