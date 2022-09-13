package com.leaderboard.service;

import com.leaderboard.dto.AggregatedResultDTO;
import com.leaderboard.dto.PlayerDTO;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import com.leaderboard.exceptions.NoResultException;
import com.leaderboard.repository.ResultRepository;
import com.leaderboard.service.interfaces.AggregateService;
import com.leaderboard.service.interfaces.StakeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AggregateServiceImpl implements AggregateService {
    private final ResultRepository resultRepository;
    private final StakeService stakeService;

    public AggregateServiceImpl(ResultRepository resultRepository,
                                StakeService stakeService) {
        this.resultRepository = resultRepository;
        this.stakeService = stakeService;
    }

    @Override
    public List<AggregatedResultDTO> getAll(Provider provider, GameType gameType) {
        return aggregate(resultRepository.getResultsByProviderAndGameType(provider, gameType));
    }

    @Override
    public List<AggregatedResultDTO> getAllByStake(Provider provider, GameType gameType, String stakeEquivalent) {
        Stake stake = stakeService.getByStakeEquivalent(BigDecimal.valueOf(Double.parseDouble(stakeEquivalent)))
                .orElseThrow(() -> new NoResultException("Stake not found by equivalent: " + stakeEquivalent));
        return aggregate(resultRepository.getResultsByProviderAndGameTypeAndStake(provider, gameType, stake));
    }

    @Override
    public List<AggregatedResultDTO> getAllByDate(LocalDate start, LocalDate end, Provider provider, GameType gameType, String stakeEquivalent) {
        if (end == null) {
            end = LocalDate.now();
        }
        List<Result> resultsByDateBetween = resultRepository.getResultsByDateBetween(start, end);
        return aggregate(resultsByDateBetween);
    }

    private List<AggregatedResultDTO> aggregate(List<Result> results) {
        Map<Stake, Map<Player, AggregatedResultDTO>> aggregateMap = new HashMap<>();

        results.forEach(result -> {
            Stake stake = result.getStake();
            if (aggregateMap.containsKey(stake)) {
                Map<Player, AggregatedResultDTO> playerAggregateMap = aggregateMap.get(stake);
                Player player = result.getPlayer();
                if (playerAggregateMap.containsKey(player)) {
                    AggregatedResultDTO aggregateResultDTO = playerAggregateMap.get(player);
                    aggregateResultDTO.setTotalPoints(aggregateResultDTO.getTotalPoints().add(result.getPoint()));
                    aggregateResultDTO.setTotalPrize(aggregateResultDTO.getTotalPrize().add(result.getPrize()));
                } else {
                    playerAggregateMap.put(
                            player,
                            new AggregatedResultDTO.Builder()
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

        List<AggregatedResultDTO> resultDTOS = new ArrayList<>();
        for (Map<Player, AggregatedResultDTO> value : aggregateMap.values()) {
            resultDTOS.addAll(value.values());
        }
        return resultDTOS;
    }

}
