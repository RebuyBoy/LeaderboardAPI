package com.leaderboard.converters;

import com.leaderboard.dto.client.gg.GGResultResponse;
import com.leaderboard.entity.Country;
import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Provider;
import com.leaderboard.entity.Result;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ResultResponseConverter {

    private final StakeConverter stakeConverter;

    public ResultResponseConverter(StakeConverter stakeConverter) {
        this.stakeConverter = stakeConverter;
    }

    public Result convert(GGResultResponse resultDTO,
                          LocalDate date,
                          String stakeStr,
                          GameType gameType,
                          Provider provider) {
        return new Result.Builder()
                .point(resultDTO.getPoints())
                .prize(resultDTO.getPrize())
                .rank(resultDTO.getRank())
                .player(getPlayer(resultDTO))
                .date(new DateLB(date))
                .stake(stakeConverter.convertToEntityAttributeByStakeEquivalent(stakeStr))
                .gameType(gameType)
                .provider(provider)
                .build();
    }

    private Player getPlayer(GGResultResponse resultDTO) {
        return new Player.Builder()
                .name(resultDTO.getName())
                .country(new Country(resultDTO.getCountryCode()))
                .build();
    }

}
