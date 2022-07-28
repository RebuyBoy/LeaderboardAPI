package com.leaderboard.repository;

import com.leaderboard.entity.Stake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface StakeRepository extends JpaRepository<Stake,Integer> {
        Optional<Stake> getByStakeEquivalent(BigDecimal stakeEquivalent);

}
