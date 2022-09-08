package com.leaderboard.converters;

import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.entity.Country;
import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ResultResponseConverter {

    private final GameTypeConverter gameTypeConverter;

    public ResultResponseConverter(GameTypeConverter gameTypeConverter) {
        this.gameTypeConverter = gameTypeConverter;
    }

    public Result convert(GGResultDTO resultDTO, LocalDate date, String stakeStr, String gameTypeName) {
        return new Result.Builder()
                .point(resultDTO.getPoints())
                .prize(resultDTO.getPrize())
                .rank(resultDTO.getRank())
                .player(getPlayer(resultDTO))
                .stake(stringToStake(stakeStr))
                .date(new DateLB(date))
                .gameType(gameTypeConverter.convertToEntityAttributeByName(gameTypeName))
                .build();
    }

    private Stake stringToStake(String stakeStr) {
        String removedDollarSign = stakeStr.substring(1);
        BigDecimal stake = BigDecimal.valueOf(Double.parseDouble(removedDollarSign));
        return new Stake(stake);
    }

    private Player getPlayer(GGResultDTO resultDTO) {
        return new Player.Builder()
                .name(resultDTO.getName())
                .country(new Country(resultDTO.getCountryCode()))
                .build();
    }

}
