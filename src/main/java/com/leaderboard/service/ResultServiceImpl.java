package com.leaderboard.service;

import com.leaderboard.entity.*;
import com.leaderboard.repository.ResultRepository;
import com.leaderboard.service.interfaces.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final PlayerService playerService;
    private final StakeService stakeService;
    private final CountryService countryService;
    private final DateService dateService;

    public ResultServiceImpl(PlayerService playerService
            , ResultRepository resultRepository
            , StakeService stakeService
            , CountryService countryService
            , DateService dateService) {
        this.playerService = playerService;
        this.resultRepository = resultRepository;
        this.stakeService = stakeService;
        this.countryService = countryService;
        this.dateService = dateService;
    }

    @Override
    public List<Result> getByDateFrom(LocalDate from) {
        return List.of();
    }

    @Override
    public List<Result> getByDateBetween(LocalDate from, LocalDate to) {
        return List.of();
    }

    @Override
    public List<Result> getAll(Provider provider, GameType gameType) {
        return resultRepository.getResultsByProviderAndGameType(provider, gameType);
    }

    @Override
    public List<Result> getAllByStake(Provider provider, GameType gameType, String stakeEquivalent) {
        Stake stake = stakeService.getByStakeEquivalent(BigDecimal.valueOf(Double.parseDouble(stakeEquivalent)));
        return resultRepository.getResultsByProviderAndGameTypeAndStake(provider, gameType, stake);
    }

    @Override
    public List<Result> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, String stakeEquivalent) {
        Stake stake = stakeService.getByStakeEquivalent(BigDecimal.valueOf(Double.parseDouble(stakeEquivalent)));
        return resultRepository.getResultsByDateBetween(start, end);
    }

    public void save(Result result) {

        Player player = playerService.createIfNotExists(result.getPlayer());

        String oldCode = player.getCountry().getCode();
        String newCode = result.getPlayer().getCountry().getCode();
        if (!newCode.equals(oldCode)) {
            Country country = countryService.getByCode(newCode);
            player.setCountry(country);
            player = playerService.save(player);
        }

        Stake stake = stakeService.createIfNotExists(result.getStake());
        DateLB date = dateService.createIfNotExist(result.getDate());

        result.setPlayer(player);
        result.setStake(stake);
        result.setDate(date);

        //TODO check if exists result
        resultRepository.save(result);
    }


}
