package com.leaderbord.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SetsDTO {
    @JsonProperty("name")
    private String date;
    private List<SubsetsDTO> subsets;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<SubsetsDTO> getSubsets() {
        return subsets;
    }

    public void setSubsets(List<SubsetsDTO> subsets) {
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
