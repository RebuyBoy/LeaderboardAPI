package com.leaderboard.service.interfaces;

import com.leaderboard.entity.Stake;

import java.math.BigDecimal;

public interface StakeService {
    Stake getByStakeEquivalent(BigDecimal stakeEquivalent);

    Stake save(Stake stake);

    Stake createIfNotExists(Stake stake);
}
