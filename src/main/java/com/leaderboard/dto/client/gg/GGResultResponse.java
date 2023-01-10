package com.leaderboard.dto.client.gg;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;


public class GGResultResponse {

    @JsonProperty("nickname")
    private String name;
    @JsonProperty("countryId")
    private String countryCode;
    private BigDecimal points;
    private BigDecimal prize;
    private int rank;
    private int stake;
    private boolean isCorrect;

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

    @JsonProperty("point")
    public void setPoints(String points) {
        this.points = BigDecimal.valueOf(Double.parseDouble(points));
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public BigDecimal getPrize() {
        return prize;
    }

    @JsonProperty("prizePaid")
    public void setPrize(Map<String, Object> prizePaid) {
        if (prizePaid == null) {
            this.prize = new BigDecimal(0);
        } else {
            Object value = prizePaid.get("value");
            if (value instanceof Double doubleValue) {
                this.prize = BigDecimal.valueOf(doubleValue);
            } else if (value instanceof Integer integerValue) {
                this.prize = BigDecimal.valueOf(integerValue);
            }
        }
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

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "GGResultResponse{" +
                "name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", points=" + points +
                ", prize=" + prize +
                ", rank=" + rank +
                ", stake=" + stake +
                ", isCorrect=" + isCorrect +
                '}';
    }

}
