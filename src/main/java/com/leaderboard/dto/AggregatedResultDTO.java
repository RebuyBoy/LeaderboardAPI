package com.leaderboard.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class AggregatedResultDTO implements Comparable<AggregatedResultDTO> {

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

    @Override
    public int compareTo(AggregatedResultDTO o) {
        int byPrize = this.totalPrize.compareTo(o.totalPrize);
        return byPrize == 0 ? this.totalPoints.compareTo(o.totalPoints) : byPrize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregatedResultDTO that = (AggregatedResultDTO) o;
        return player.equals(that.player) && totalPrize.equals(that.totalPrize) && totalPoints.equals(that.totalPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, totalPrize, totalPoints);
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