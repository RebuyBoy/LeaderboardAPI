package com.leaderboard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;


public class GGResultDTO {
    @JsonProperty("nickname")
    private String name;
    @JsonProperty("countryId")
    private String countryCode;
    private BigDecimal points;
    private BigDecimal prize;
    private int rank;
    private int stake;

    @JsonProperty("prize")
    public void setPrize(Map<String, String> prize) {
        this.prize = prize == null
                ? new BigDecimal(0)
                : BigDecimal.valueOf(Double.parseDouble(prize.get("value")));
    }

    @JsonProperty("point")
    public void setPoints(String points) {
        this.points = BigDecimal.valueOf(Double.parseDouble(points));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    public void setPrize(BigDecimal prize) {
        this.prize = prize;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }



    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    @Override
    public String toString() {
        return "GGPlayerResultDTO{" +
                "name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", points=" + points +
                ", prize=" + prize +
                ", rank=" + rank +
                '}';
    }
}
