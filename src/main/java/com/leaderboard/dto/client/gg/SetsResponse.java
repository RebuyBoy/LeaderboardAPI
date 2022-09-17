package com.leaderboard.dto.client.gg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class SetsResponse {

    @JsonProperty("name")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private LocalDate date;
    private List<SubsetsResponse> subsets;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
