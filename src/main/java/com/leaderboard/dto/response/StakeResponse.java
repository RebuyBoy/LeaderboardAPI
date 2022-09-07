package com.leaderboard.dto.response;

import java.math.BigDecimal;

public class StakeResponse {
    private BigDecimal StakeEquivalent;

    public StakeResponse(BigDecimal stakeEquivalent) {
        StakeEquivalent = stakeEquivalent;
    }

    public BigDecimal getStakeEquivalent() {
        return StakeEquivalent;
    }

    @Override
    public String toString() {
        return "StakeDTO{" +
                "StakeEquivalent=" + StakeEquivalent +
                '}';
    }
}
