package com.leaderbord.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubsetsDTO {
    private int promotionId;
    @JsonProperty("name")
    private String stake;

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public String getStake() {
        return stake;
    }

    public void setStake(String stake) {
        this.stake = stake;
    }

    @Override
    public String toString() {
        return "SubsetsDTO{" +
                "promotionId=" + promotionId +
                ", stake='" + stake + '\'' +
                '}';
    }
}
