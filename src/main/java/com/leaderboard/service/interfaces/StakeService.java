package com.leaderboard.service.interfaces;

import com.leaderboard.entity.Stake;

import java.math.BigDecimal;
import java.util.Optional;

public interface StakeService {
    Optional<Stake> getByStakeEquivalent(BigDecimal stakeEquivalent);
    Stake save(Stake stake);
    Stake createIfNotExists(Stake stake);
}
