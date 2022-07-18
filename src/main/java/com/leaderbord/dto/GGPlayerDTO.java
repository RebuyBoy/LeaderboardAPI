package com.leaderbord.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GGPlayerDTO {
    @JsonProperty("nickname")
    private String name;
    @JsonProperty("countryId")
    private String countryCode;
    private BigDecimal points;
    private BigDecimal prize;
    private int rank;

    @JsonProperty("prize")
    public void setPrize(Map<String, String> prize) {
        this.prize = BigDecimal.valueOf(Double.parseDouble(prize.get("value")));
    }
    @JsonProperty("point")
    public void setPoints(String points) {
        this.points = BigDecimal.valueOf(Double.parseDouble(points));
    }

//    {"nickname": "tzzd2020",
//            "rank": 1,
//            "countryId": "CN",
//            "point": "3083.00",
//            "prize": {
//        "currencyId": "GCD",
//                "value": 55,
//                "description": null,
//                "updatedAt": null,
//                "prizeItem": null
//    },
//        "prizePaid": {
//        "currencyId": null,
//                "value": 0,
//                "description": null,
//                "updatedAt": null,
//                "prizeItem": null
//    },
//        "isCorrect": true}
}

