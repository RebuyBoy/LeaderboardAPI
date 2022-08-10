package com.leaderboard.service;

import com.leaderboard.dto.AggregatedResultDTO;
import com.leaderboard.dto.PlayerDTO;
import com.leaderboard.dto.response.StakeDTO;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import com.leaderboard.repository.ResultRepository;
import com.leaderboard.service.interfaces.AggregateService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AggregateServiceImpl implements AggregateService {
    private final ResultRepository resultRepository;

    public AggregateServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<AggregatedResultDTO> getAll() {
        return aggregate(resultRepository.findAll());
    }

    @Override
    public List<AggregatedResultDTO> getAllByName(String name) {
        return Collections.emptyList();
        //TODO
    }

    @Override
    public List<AggregatedResultDTO> getAllByDate(LocalDate start, LocalDate end) {
        //TODO
        return null;
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
                                    .stake(new StakeDTO(result.getStake().getStakeEquivalent()))
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
