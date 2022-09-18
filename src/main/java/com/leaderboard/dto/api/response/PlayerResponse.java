package com.leaderboard.dto.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leaderboard.entity.Country;

public class PlayerResponse {

    private final String name;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private final Country country;

    private PlayerResponse(Builder builder) {
        this.name = builder.name;
        this.country = builder.country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public static class Builder {
        private String name;
        private Country country;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder country(Country country) {
            this.country = country;
            return this;
        }

        public PlayerResponse build() {
            return new PlayerResponse(this);
        }
    }

}
