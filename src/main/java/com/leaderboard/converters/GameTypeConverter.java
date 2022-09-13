package com.leaderboard.converters;

import com.leaderboard.entity.GameType;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
@Component
public class GameTypeConverter implements AttributeConverter<GameType, String> {

    @Override
    public String convertToDatabaseColumn(GameType gameType) {
        return gameType.getCode();
    }

    @Override
    public GameType convertToEntityAttribute(String code) {
        return Stream.of(GameType.values())
                .filter(gameType -> gameType.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public GameType convertToEntityAttributeByName(String name) {
        return Stream.of(GameType.values())
                .filter(gameType -> gameType.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
