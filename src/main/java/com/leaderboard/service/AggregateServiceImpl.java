package com.leaderboard.service;

import com.leaderboard.dto.api.AggregatedResult;
import com.leaderboard.dto.api.response.PlayerResponse;
import com.leaderboard.dto.api.response.ProviderResponse;
import com.leaderboard.dto.api.response.ResultResponse;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import com.leaderboard.service.interfaces.AggregateService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.leaderboard.constants.Constants.GMT_MINUS_8;

@Service
public class AggregateServiceImpl implements AggregateService {

    private final ResultService resultService;

    public AggregateServiceImpl(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public ResultResponse getAllByStake(Provider provider, GameType gameType, Stake stake) {
        List<AggregatedResult> aggregate = aggregate(resultService.getAllByStake(provider, gameType, stake));
        return new ResultResponse(new ProviderResponse(provider.name(), provider.getDescription(), provider.getCurrency()), aggregate);
    }

    @Override
    public ResultResponse getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, Stake stake) {
        if (end == null) {
            end = LocalDate.now((ZoneId.of(GMT_MINUS_8)));
        }
        List<AggregatedResult> aggregatedResultsByDateBetween = aggregate(resultService.getAllByDate(start, end, provider, gameType, stake));
        return new ResultResponse(new ProviderResponse(provider.name(), provider.getDescription(), provider.getCurrency()), aggregatedResultsByDateBetween);
    }

    private List<AggregatedResult> aggregate(List<Result> results) {
        Map<Player, AggregatedResult> playerAggregatedResult = new HashMap<>();

        for (Result result : results) {
            Player player = result.getPlayer();
            playerAggregatedResult.computeIfPresent(player, (p, aggregatedResult) -> summarizeResults(aggregatedResult, result));
            playerAggregatedResult.putIfAbsent(player, createAggregatedResult(player, result));
        }
        return playerAggregatedResult.values().stream().sorted().toList();
    }

    private AggregatedResult summarizeResults(AggregatedResult aggregatedResult, Result result) {
        return new AggregatedResult.Builder()
                .player(aggregatedResult.getPlayer())
                .totalPoints(aggregatedResult.getTotalPoints().add(result.getPoints()))
                .totalPrize(aggregatedResult.getTotalPrize().add(result.getPrize()))
                .build();
    }

    private AggregatedResult createAggregatedResult(Player player, Result result) {
        return new AggregatedResult.Builder()
                .player(toPlayerResponse(player))
                .totalPoints(result.getPoints())
                .totalPrize(result.getPrize())
                .build();
    }

    private PlayerResponse toPlayerResponse(Player player) {
        return new PlayerResponse.Builder()
                .name(player.getName())
                .country(player.getCountry())
                .build();
    }

}
