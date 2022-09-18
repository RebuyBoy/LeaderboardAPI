package com.leaderboard.converters;

import com.leaderboard.entity.Stake;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.math.BigDecimal;
import java.util.stream.Stream;

@Converter(autoApply = true)
@Component
public class StakeConverter implements AttributeConverter<Stake, String> {

    @Override
    public String convertToDatabaseColumn(Stake stake) {
        return stake == null ? null : stake.name();
    }

    @Override
    public Stake convertToEntityAttribute(String name) {
        return Stream.of(Stake.values())
                .filter(stake -> stake.name().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stake not found by name " + name));
    }

    public Stake convertToEntityAttributeByStakeEquivalent(String stakeStr) {
        String removedDollarSign = stakeStr.substring(1);
        BigDecimal stakeEquivalent = BigDecimal.valueOf(Double.parseDouble(removedDollarSign));
        return Stream.of(Stake.values())
                .filter(stake -> stake.getStakeEquivalent().equals(stakeEquivalent))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stake not found by stake equivalent " + stakeEquivalent));
    }

}

