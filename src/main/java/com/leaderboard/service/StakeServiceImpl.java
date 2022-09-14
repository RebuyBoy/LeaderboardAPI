package com.leaderboard.service;

import com.leaderboard.entity.Stake;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.repository.StakeRepository;
import com.leaderboard.service.interfaces.StakeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class StakeServiceImpl implements StakeService {
    private final StakeRepository stakeRepository;

    public StakeServiceImpl(StakeRepository stakeRepository) {
        this.stakeRepository = stakeRepository;
    }

    @Override
    public Stake getByStakeEquivalent(BigDecimal stakeEquivalent) {
        return stakeRepository.getByStakeEquivalent(stakeEquivalent)
                .orElseThrow(() -> new NoResultException("Stake not found by equivalent: " + stakeEquivalent));
    }

    @Override
    public Stake save(Stake stake) {
        return stakeRepository.save(stake);
    }

    @Override
    public Stake createIfNotExists(Stake stake) {
        Optional<Stake> optionalStake = stakeRepository.getByStakeEquivalent(stake.getStakeEquivalent());
        return optionalStake.isEmpty()
                ? save(stake)
                : optionalStake.get();
    }
}
