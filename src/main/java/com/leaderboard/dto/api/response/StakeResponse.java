package com.leaderboard.dto.api.response;

public class StakeResponse {
  //TODO stake code SD_1000
  //value 1000
    //remove value
    private String value;
    private String name;
    private String description;

    public StakeResponse(String value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "StakeResponse{" +
                "value='" + value + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
