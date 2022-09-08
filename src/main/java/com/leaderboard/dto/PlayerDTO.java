package com.leaderboard.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leaderboard.entity.Country;

public class PlayerDTO {
    private final String name;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private final Country country;

    private PlayerDTO(Builder builder) {
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

        public PlayerDTO build() {
            return new PlayerDTO(this);
        }
    }
}
