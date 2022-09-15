package com.leaderboard.dto.response;

import java.math.BigDecimal;

public class StakeResponse {
    private BigDecimal stakeEquivalent;

    public StakeResponse(BigDecimal stakeEquivalent) {
        this.stakeEquivalent = stakeEquivalent;
    }

    public BigDecimal getStakeEquivalent() {
        return stakeEquivalent;
    }

    @Override
    public String toString() {
        return "StakeDTO{" +
                "StakeEquivalent=" + stakeEquivalent +
                '}';
    }
}
