package com.leaderboard.service;

import com.leaderboard.dto.AggregatedResult;
import com.leaderboard.dto.PlayerDTO;
import com.leaderboard.entity.*;
import com.leaderboard.service.interfaces.AggregateService;
import com.leaderboard.service.interfaces.ResultService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AggregateServiceImpl implements AggregateService {

    private final ResultService resultService;

    public AggregateServiceImpl(ResultService resultService) {
        this.resultService = resultService;
    }

    @Override
    public List<AggregatedResult> getAll(Provider provider, GameType gameType) {
        return aggregate(resultService.getAll(provider, gameType));
    }

    @Override
    public List<AggregatedResult> getAllByStake(Provider provider, GameType gameType, String stakeEquivalent) {
        return aggregate(resultService.getAllByStake(provider, gameType, stakeEquivalent));
    }

    @Override
    public List<AggregatedResult> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, String stakeEquivalent) {
        if (end == null) {
            end = LocalDate.now();
        }
        List<Result> resultsByDateBetween = resultService.getAllByDate(start, end, provider, gameType, stakeEquivalent);
        return aggregate(resultsByDateBetween);
    }

    private List<AggregatedResult> aggregate(List<Result> results) {
        Map<Stake, Map<Player, AggregatedResult>> aggregateMap = new HashMap<>();

        results.forEach(result -> {
            Stake stake = result.getStake();
            if (aggregateMap.containsKey(stake)) {
                Map<Player, AggregatedResult> playerAggregateMap = aggregateMap.get(stake);
                Player player = result.getPlayer();
                if (playerAggregateMap.containsKey(player)) {
                    AggregatedResult aggregateResultDTO = playerAggregateMap.get(player);
                    aggregateResultDTO.setTotalPoints(aggregateResultDTO.getTotalPoints().add(result.getPoint()));
                    aggregateResultDTO.setTotalPrize(aggregateResultDTO.getTotalPrize().add(result.getPrize()));
                } else {
                    playerAggregateMap.put(
                            player,
                            new AggregatedResult.Builder()
//                                    .stake(new StakeResponse(result.getStake().getStakeEquivalent()))
                                    .player(new PlayerDTO.Builder()
                                            .name(player.getName())
                                            .country(player.getCountry())
                                            .build())
                                    .totalPoints(result.getPoint())
                                    .totalPrize(result.getPrize())
                                    .build());
                }
            } else {
                aggregateMap.put(stake, new HashMap<>());
            }
        });

        List<AggregatedResult> resultDTOS = new ArrayList<>();
        for (Map<Player, AggregatedResult> value : aggregateMap.values()) {
            resultDTOS.addAll(value.values());
        }
        return resultDTOS;
    }

}
