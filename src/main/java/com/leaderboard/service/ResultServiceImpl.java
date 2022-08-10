package com.leaderboard.service;

import com.leaderboard.entity.Country;
import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import com.leaderboard.repository.ResultRepository;
import com.leaderboard.service.interfaces.CountryService;
import com.leaderboard.service.interfaces.DateService;
import com.leaderboard.service.interfaces.PlayerService;
import com.leaderboard.service.interfaces.ResultService;
import com.leaderboard.service.interfaces.StakeService;
import org.springframework.stereotype.Service;

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
    public List<Result> getAll() {
        return resultRepository.findAll();
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
