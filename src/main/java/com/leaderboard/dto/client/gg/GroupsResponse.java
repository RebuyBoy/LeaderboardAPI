package com.leaderboard.dto.client.gg;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class GroupsResponse {

    private int groupId;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private LocalDateTime startedAt;
    private String[] gameTypes;
    private String host;
    private List<SetsResponse> sets;

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

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "GroupsResponse{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", startedAt=" + startedAt +
                ", gameTypes=" + Arrays.toString(gameTypes) +
                ", host='" + host + '\'' +
                ", sets=" + sets +
                '}';
    }

}
