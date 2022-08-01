package com.leaderboard.converters;

import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.entity.Country;
import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class ResultDtoResultConverter {
    private final GameTypeConverter gameTypeConverter;

    public ResultDtoResultConverter(GameTypeConverter gameTypeConverter) {
        this.gameTypeConverter = gameTypeConverter;
    }

    public Result dtoToResult(GGResultDTO resultDTO, LocalDate date, String stake, String gameTypeName) {
        BigDecimal prize = resultDTO.getPrize();
        BigDecimal points = resultDTO.getPoints();
        int rank = resultDTO.getRank();

        String countryCode = resultDTO.getCountryCode();
        Country country = new Country(countryCode);

        String name = resultDTO.getName();
        Player player = new Player(name, country);

        Stake stake1 = new Stake(BigDecimal.valueOf(Double.parseDouble(stake.substring(1))));
        DateLB dateLB = new DateLB(Timestamp.valueOf(date.atTime(LocalTime.MIDNIGHT)));
        GameType gameType = gameTypeConverter.convertToEntityAttributeByName(gameTypeName);

        return new Result.Builder().point(points)
                .prize(prize)
                .rank(rank)
                .player(player)
                .stake(stake1)
                .date(dateLB)
                .gameType(gameType)
                .build();
    }

}
