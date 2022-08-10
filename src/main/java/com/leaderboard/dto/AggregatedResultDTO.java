package com.leaderboard.dto;

import com.leaderboard.dto.response.StakeDTO;

import java.math.BigDecimal;

public class AggregatedResultDTO {

    private final StakeDTO stake;
    private final PlayerDTO player;
    private BigDecimal totalPrize;
    private BigDecimal totalPoints;

    //TODO    private int daysPlayed;
//    private BigDecimal averageRank;
//    private BigDecimal pointsPerDay;
//    private BigDecimal prizePerDay;

    private AggregatedResultDTO(Builder builder) {
        this.stake = builder.stake;
        this.player = builder.player;
        this.totalPrize = builder.totalPrize;
        this.totalPoints = builder.totalPoints;
    }

    public StakeDTO getStake() {
        return stake;
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
                "stake=" + stake +
                ", player=" + player.getName() +
                ", totalPrize=" + totalPrize +
                ", totalPoints=" + totalPoints +
                '}';
    }

    public static class Builder {
        private StakeDTO stake;
        private PlayerDTO player;
        private BigDecimal totalPrize;
        private BigDecimal totalPoints;

        public Builder stake(StakeDTO stake) {
            this.stake = stake;
            return this;
        }

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
            //TODO validate
            return new AggregatedResultDTO(this);
        }
    }



}
