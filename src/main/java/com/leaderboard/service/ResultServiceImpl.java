package com.leaderboard.service;

import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import com.leaderboard.repository.ResultRepository;
import com.leaderboard.service.interfaces.DateService;
import com.leaderboard.service.interfaces.PlayerService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final PlayerService playerService;
    private final DateService dateService;

    public ResultServiceImpl(ResultRepository resultRepository, PlayerService playerService, DateService dateService) {
        this.resultRepository = resultRepository;
        this.playerService = playerService;
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

    @Override
    public void saveIfNotExists(Result result) {

        Player player = getPlayer(result.getPlayer());
        DateLB date = getDateLB(result);

        result.setPlayer(player);
        result.setDate(date);

        saveResult(result);
    }

    private void saveResult(Result result) {
        ExampleMatcher ignoringIdMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id");
        Example<Result> example = Example.of(result, ignoringIdMatcher);
        if (!resultRepository.exists(example)) {
            resultRepository.save(result);
        }
    }

    private DateLB getDateLB(Result result) {
        return dateService.createIfNotExist(result.getDate());
    }

    private Player getPlayer(Player newPlayer) {
        Optional<Player> optionalPlayer = playerService.getByName(newPlayer.getName());
        if (optionalPlayer.isPresent()) {
            Player currentPlayer = optionalPlayer.get();
            playerService.updatePlayer(newPlayer, currentPlayer);
            return currentPlayer;
        } else {
            return playerService.save(newPlayer);
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
