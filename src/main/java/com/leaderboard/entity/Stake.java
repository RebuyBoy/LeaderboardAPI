package com.leaderboard.entity;

import java.math.BigDecimal;

public enum Stake {
    USD_2000(BigDecimal.valueOf(20.0)),
    USD_1000(BigDecimal.valueOf(10.0)),
    USD_500(BigDecimal.valueOf(5.0)),
    USD_200(BigDecimal.valueOf(2.0)),
    USD_100(BigDecimal.valueOf(1.0)),
    USD_50(BigDecimal.valueOf(0.5)),
    USD_25(BigDecimal.valueOf(0.25)),
    USD_10(BigDecimal.valueOf(0.1));

    private final BigDecimal stakeEquivalent;

    Stake(BigDecimal stakeEquivalent) {
        this.stakeEquivalent = stakeEquivalent;
    }

    public BigDecimal getStakeEquivalent() {
        return stakeEquivalent;
    }

}
