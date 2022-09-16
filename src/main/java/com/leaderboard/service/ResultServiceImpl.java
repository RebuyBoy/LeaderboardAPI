package com.leaderboard.service;

import com.leaderboard.entity.Country;
import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import com.leaderboard.repository.ResultRepository;
import com.leaderboard.service.interfaces.CountryService;
import com.leaderboard.service.interfaces.DateService;
import com.leaderboard.service.interfaces.PlayerService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final PlayerService playerService;
    private final CountryService countryService;
    private final DateService dateService;

    public ResultServiceImpl(PlayerService playerService
            , ResultRepository resultRepository
            , CountryService countryService
            , DateService dateService) {
        this.playerService = playerService;
        this.resultRepository = resultRepository;
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
    public List<Result> getAllByStake(Provider provider, GameType gameType, Stake stake) {
        return resultRepository.getResultsByProviderAndGameTypeAndStake(provider, gameType, stake);
    }

    @Override
    public List<Result> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, Stake stake) {
        return resultRepository.getResultsByDateBetween(start, end, provider, gameType, stake);
    }

    public void saveIfNotExists(Result result) {

        Player player = playerService.createIfNotExists(result.getPlayer());

        String oldCode = player.getCountry().getCode();
        String newCode = result.getPlayer().getCountry().getCode();
        if (!newCode.equals(oldCode)) {
            Country country = countryService.getByCode(newCode);
            player.setCountry(country);
            player = playerService.save(player);
        }

        DateLB date = dateService.createIfNotExist(result.getDate());

        result.setPlayer(player);
        result.setDate(date);

        ExampleMatcher ignoringIdMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id");
        Example<Result> example = Example.of(result, ignoringIdMatcher);
        if (!resultRepository.exists(example)) {
            resultRepository.save(result);
        }
    }

    @Override
    public List<Provider> getAllProviders() {
        return resultRepository.getDistinctByProvider();
    }

    @Override
    public LocalDate getLastUpdateByProvider(Provider provider) {
        return resultRepository.getLastUpdateByProvider(provider);
    }

    @Override
    public List<GameType> getGameTypesDataByProvider(Provider provider) {
        return resultRepository.getGameTypeDistinctByProvider(provider);
    }

    @Override
    public List<Stake> getStakesByByProviderAndGameType(Provider provider, GameType gameType) {
        return resultRepository.getStakeDistinctByProviderAndGameType(provider, gameType);
    }

}
