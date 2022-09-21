package com.leaderboard.entity;

import java.math.BigDecimal;

public enum Stake {

    SD_1000(BigDecimal.valueOf(10.0), "$10.00"),
    SD_500(BigDecimal.valueOf(5.0), "$5.00"),
    SD_200(BigDecimal.valueOf(2.0), "$2.00"),
    SD_100(BigDecimal.valueOf(1.0), "$1.00"),
    SD_50(BigDecimal.valueOf(0.5), "$0.50"),
    SD_25(BigDecimal.valueOf(0.25), "$0.25"),
    SD_10(BigDecimal.valueOf(0.1), "$0.10"),
    OM_2000(BigDecimal.valueOf(20.0), "$10/$20"),
    OM_1000(BigDecimal.valueOf(10.0), "$5/$10"),
    OM_500(BigDecimal.valueOf(5.0), "$2/$5"),
    OM_200(BigDecimal.valueOf(2.0), "$1/$2"),
    OM_100(BigDecimal.valueOf(1.0), "$0.50/$1.00"),
    OM_50(BigDecimal.valueOf(0.5), "$0.25/$0.50"),
    OM_25(BigDecimal.valueOf(0.25), "$0.10/$0.25"),
    OM_10(BigDecimal.valueOf(0.1), "$0.05/$0.10");

    private final BigDecimal stakeEquivalent;
    private final String description;

    Stake(BigDecimal stakeEquivalent, String description) {
        this.stakeEquivalent = stakeEquivalent;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getStakeEquivalent() {
        return stakeEquivalent;
    }

}
