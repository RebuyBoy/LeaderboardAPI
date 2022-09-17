package com.leaderboard.dto.api;

import com.leaderboard.dto.api.response.PlayerResponse;

import java.math.BigDecimal;
import java.util.Objects;

public class AggregatedResult implements Comparable<AggregatedResult> {

    private final PlayerResponse player;
    private BigDecimal totalPrize;
    private BigDecimal totalPoints;

    private AggregatedResult(Builder builder) {
        this.player = builder.player;
        this.totalPrize = builder.totalPrize;
        this.totalPoints = builder.totalPoints;
    }

    public PlayerResponse getPlayer() {
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
    public int compareTo(AggregatedResult o) {
        int byPrize = this.totalPrize.compareTo(o.totalPrize) * -1;
        return byPrize == 0 ? this.totalPoints.compareTo(o.totalPoints) * -1 : byPrize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregatedResult that = (AggregatedResult) o;
        return player.equals(that.player) && totalPrize.equals(that.totalPrize) && totalPoints.equals(that.totalPoints);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, totalPrize, totalPoints);
    }


    public static class Builder {
        private PlayerResponse player;
        private BigDecimal totalPrize;
        private BigDecimal totalPoints;

        public Builder player(PlayerResponse player) {
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

        public AggregatedResult build() {
            return new AggregatedResult(this);
        }
    }

}