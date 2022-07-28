package com.leaderboard.service;

import com.leaderboard.entity.Stake;
import com.leaderboard.repository.StakeRepository;
import com.leaderboard.service.interfaces.StakeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class GGStakeService implements StakeService {
    private final StakeRepository stakeRepository;

    public GGStakeService(StakeRepository stakeRepository) {
        this.stakeRepository = stakeRepository;
    }

    @Override
    public Optional<Stake> getByStakeEquivalent(BigDecimal stakeEquivalent) {
        return stakeRepository.getByStakeEquivalent(stakeEquivalent);
    }

    @Override
    public Stake save(Stake stake) {
        return stakeRepository.save(stake);
    }

    @Override
    public Stake createIfNotExists(Stake stake) {
        Optional<Stake> optionalStake = getByStakeEquivalent(stake.getStakeEquivalent());
        return optionalStake.isEmpty()
                ? save(stake)
                : optionalStake.get();
    }
}
