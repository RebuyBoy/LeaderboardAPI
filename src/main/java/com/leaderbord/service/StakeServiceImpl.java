package com.leaderbord.service;

import com.leaderbord.entity.Stake;
import com.leaderbord.repository.StakeRepository;
import com.leaderbord.service.interfaces.StakeService;
import org.springframework.stereotype.Service;

@Service
public class StakeServiceImpl implements StakeService {
    private final StakeRepository stakeRepository;

    public StakeServiceImpl(StakeRepository stakeRepository) {
        this.stakeRepository = stakeRepository;
    }

    @Override
    public Stake getById(int id) {
        return stakeRepository.getById(id);
    }
}
