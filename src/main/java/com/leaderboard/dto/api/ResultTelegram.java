package com.leaderboard.dto.api;

import java.math.BigDecimal;

public class ResultTelegram {
    private String name;
    private BigDecimal points;

    public ResultTelegram(String name, BigDecimal points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
