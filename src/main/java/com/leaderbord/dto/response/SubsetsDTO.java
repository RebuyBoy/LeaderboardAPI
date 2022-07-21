package com.leaderbord.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SubsetsDTO {
    private int promotionId;
    @JsonProperty("name")
    private String stake;

    @Override
    public String toString() {
        return "SubsetsDTO{" +
                "promotionID=" + promotionId +
                ", stake='" + stake + '\'' +
                '}';
    }
}
