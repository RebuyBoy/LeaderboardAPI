package com.leaderboard.entity;

import java.math.BigDecimal;

public enum Stake {
    USD_2000("USD", BigDecimal.valueOf(20.0)),
    USD_1000("USD", BigDecimal.valueOf(10.0)),
    USD_500("USD", BigDecimal.valueOf(5.0)),
    USD_200("USD", BigDecimal.valueOf(2.0)),
    USD_100("USD", BigDecimal.valueOf(1.0)),
    USD_50("USD", BigDecimal.valueOf(0.5)),
    USD_25("USD", BigDecimal.valueOf(0.25)),
    USD_10("USD", BigDecimal.valueOf(0.1));

    private final String currency;
    private final BigDecimal stakeEquivalent;

    Stake(String currency, BigDecimal stakeEquivalent) {
        this.currency = currency;
        this.stakeEquivalent = stakeEquivalent;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getStakeEquivalent() {
        return stakeEquivalent;
    }

}
