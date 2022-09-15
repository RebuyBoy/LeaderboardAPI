package com.leaderboard.converters;

import com.leaderboard.dto.client.gg.GGResultResponse;
import com.leaderboard.entity.Country;
import com.leaderboard.entity.DateLB;
import com.leaderboard.entity.Player;
import com.leaderboard.entity.Result;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ResultResponseConverter {

    private final GameTypeConverter gameTypeConverter;
    private final ProviderConverter providerConverter;
    private final StakeConverter stakeConverter;

    public ResultResponseConverter(GameTypeConverter gameTypeConverter,
                                   ProviderConverter providerConverter,
                                   StakeConverter stakeConverter) {
        this.gameTypeConverter = gameTypeConverter;
        this.providerConverter = providerConverter;
        this.stakeConverter = stakeConverter;
    }

    public Result convert(GGResultResponse resultDTO,
                          LocalDate date,
                          String stakeStr,
                          String gameTypeName,
                          String provider) {
        return new Result.Builder()
                .point(resultDTO.getPoints())
                .prize(resultDTO.getPrize())
                .rank(resultDTO.getRank())
                .player(getPlayer(resultDTO))
                .date(new DateLB(date))
                .stake(stakeConverter.convertToEntityAttributeByStakeEquivalent(stakeStr))
                .gameType(gameTypeConverter.convertToEntityAttributeByName(gameTypeName))
                .provider(providerConverter.convertToEntityAttributeByName(provider))
                .build();
    }

    private Player getPlayer(GGResultResponse resultDTO) {
        return new Player.Builder()
                .name(resultDTO.getName())
                .country(new Country(resultDTO.getCountryCode()))
                .build();
    }

}
