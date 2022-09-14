package com.leaderboard.dto;

import java.math.BigDecimal;

public class AggregatedResultDTO {

    private final PlayerDTO player;
    private BigDecimal totalPrize;
    private BigDecimal totalPoints;

    private AggregatedResultDTO(Builder builder) {
        this.player = builder.player;
        this.totalPrize = builder.totalPrize;
        this.totalPoints = builder.totalPoints;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public BigDecimal getTotalPrize() {
        return totalPrize;
    }

    public BigDecimal getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPrize(BigDecimal totalPrize) {
        this.totalPrize = totalPrize;
    }

    public void setTotalPoints(BigDecimal totalPoints) {
        this.totalPoints = totalPoints;
    }

    @Override
    public String toString() {
        return "AggregatedResultDTO{" +
                ", player=" + player.getName() +
                ", totalPrize=" + totalPrize +
                ", totalPoints=" + totalPoints +
                '}';
    }

    public static class Builder {
        private PlayerDTO player;
        private BigDecimal totalPrize;
        private BigDecimal totalPoints;

        public Builder player(PlayerDTO player) {
            this.player = player;
            return this;
        }

        public Builder totalPrize(BigDecimal totalPrize) {
            this.totalPrize = totalPrize;
            return this;
        }

        public Builder totalPoints(BigDecimal totalPoints) {
            this.totalPoints = totalPoints;
            return this;
        }

        public AggregatedResultDTO build() {
            return new AggregatedResultDTO(this);
        }
    }

}