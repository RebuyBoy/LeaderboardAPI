package com.leaderboard.constants;

import com.leaderboard.entity.GameType;

import java.util.Map;
import java.util.Set;

public class Constants {

    //TODO default time zone?
    public static final String GMT_MINUS_8 = "GMT-8";
    private static final Set<String> SUITABLE_STAKES_SHORT_DECK = Set.of(
            "$10.00",
            "$5.00",
            "$2.00",
            "$1.00",
            "$0.50",
            "$0.25",
            "$0.10"
    );

    private static final Set<String> SUITABLE_STAKES_OMAHA = Set.of(
            "$10/$20",
            "$5/$10",
            "$2/$5",
            "$1/$2",
            "$0.50/$1.00",
            "$0.25/$0.50",
            "$0.10/$0.25",
            "$0.05/$0.10"
    );

    public static final Map<GameType, Set<String>> GAME_TYPES_SUITABLE_STAKES = Map.of(
            GameType.SHORT_DECK, SUITABLE_STAKES_SHORT_DECK
            //,GameType.OMAHA, SUITABLE_STAKES_OMAHA
    );

    private static final Map<String, String> STAKES_TO_PART_OF_URL_SHORT_DECK = Map.of(
            "$10.00", "10",
            "$5.00", "15",
            "$2.00", "20",
            "$1.00", "25",
            "$0.50", "35",
            "$0.25", "50",
            "$0.10", "60",
            "$0.05", "70",
            "$0.02", "120"
    );

    private static final Map<String, String> STAKES_TO_PART_OF_URL_OMAHA = Map.of(
            "$10/$20", "30",
            "$5/$10", "60",
            "$2/$5", "85",
            "$1/$2", "120",
            "$0.50/$1.00", "120",
            "$0.25/$0.50", "150",
            "$0.10/$0.25", "150",
            "$0.05/$0.10", "150"
    );

    public static final Map<GameType, Map<String, String>> GAME_TYPES_PART_OF_URL = Map.of(
            GameType.SHORT_DECK, STAKES_TO_PART_OF_URL_SHORT_DECK
//            ,GameType.OMAHA, STAKES_TO_PART_OF_URL_OMAHA
    );

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

}
