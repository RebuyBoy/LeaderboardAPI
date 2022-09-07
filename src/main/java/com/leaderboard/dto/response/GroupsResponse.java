package com.leaderboard.dto.response;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class GroupsResponse {

    int groupId;
    String name;
    //Можно подключить модуль Jackson time и поменять на LocalDate
    Timestamp startedAt;
    String[] gameTypes;
    List<SetsResponse> sets;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getGameTypes() {
        return gameTypes;
    }

    public void setGameTypes(String[] gameTypes) {
        this.gameTypes = gameTypes;
    }

    public List<SetsResponse> getSets() {
        return sets;
    }

    public void setSets(List<SetsResponse> sets) {
        this.sets = sets;
    }

    public Timestamp getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Timestamp startedAt) {
        this.startedAt = startedAt;
    }

    @Override
    public String toString() {
        return "GroupsResponse{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", startedAt=" + startedAt +
                ", gameTypes=" + Arrays.toString(gameTypes) +
                ", sets=" + sets +
                '}';
    }

}
