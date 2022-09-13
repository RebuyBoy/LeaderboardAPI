package com.leaderboard.constants;

import java.util.Map;
import java.util.Set;

public class Constants {

    public static final String GGN_GROUP_ID_REQUEST_BASE = "https://pml.good-game-network.com/lapi/leaderboard/groups/";
    public static final String GGN_SHORT_DECK_PROMO_URL = "https://play.pokerok900.com/promotions/promo-short-deck";
    public static final String PROMO_URL_FORMAT = "https://pml.good-game-network.com/lapi/leaderboard/%s/?status=PENDING&status=OPTED_IN&status=COMPLETED&status=EXPIRED&limit=%s&hasSummary=true&hasSummaryPaidPrizes=true&hasSummaryPrizeItem=true";
    public static final String SECRET_KEY = "milliseconds";
    public static final String EVERY_DAY_PLUS_3_MINUTES_AFTER_MIDNIGHT_CRON = "0 3 0 * * *";
    public static final String GMT_MINUS_8 = "GMT-8";
    public static final Set<String> SUITABLE_STAKES = Set.of(
            "$10.00"
            , "$5.00"
            , "$2.00"
            , "$1.00"
            , "$0.50"
            , "$0.25"
            , "$0.10"
    );
    public static final Map<String, String> STAKES_TO_PART_OF_URL = Map.of(
            "$10.00", "10"
            , "$5.00", "15"
            , "$2.00", "20"
            , "$1.00", "20"
            , "$0.50", "35"
            , "$0.25", "50"
            , "$0.10", "60"
            , "$0.05", "70"
            , "$0.02", "120"
    );

    private Constants() {
        throw new IllegalStateException("Utility class");
    }
}
