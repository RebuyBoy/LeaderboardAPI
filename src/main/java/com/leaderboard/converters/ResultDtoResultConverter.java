package com.leaderboard.converters;

import com.leaderboard.dto.GGResultDTO;
import com.leaderboard.entity.Country;
import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Result;
import com.leaderboard.entity.Stake;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
//TODO -> Json converter
public class ResultDtoResultConverter {

    public Result dtoToResult(GGResultDTO resultDTO, LocalDate date, String stake) {
        BigDecimal prize = resultDTO.getPrize();
        BigDecimal points = resultDTO.getPoints();
        int rank = resultDTO.getRank();


        String countryCode = resultDTO.getCountryCode();
        Country country = new Country(countryCode);

        String name = resultDTO.getName();
        Player player = new Player(name, country);

        Stake stake1 = new Stake(BigDecimal.valueOf(Double.parseDouble(stake.substring(1))));
        DateLB dateLB = new DateLB(Timestamp.valueOf(date.atTime(LocalTime.MIDNIGHT)));
        //STAKE  stakeEquivalent
        //DATE   timestamp
        //TODO Result builder
        Result result = new Result();
        result.setPoint(points);
        result.setPrize(prize);
        result.setRank(rank);
        result.setPlayer(player);
        result.setStake(stake1);
        result.setDate(dateLB);
        return result;
    }

}
