package com.leaderboard.dto.response;

import java.math.BigDecimal;

public class StakeDTO {
    private BigDecimal StakeEquivalent;

    public StakeDTO(BigDecimal stakeEquivalent) {
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
