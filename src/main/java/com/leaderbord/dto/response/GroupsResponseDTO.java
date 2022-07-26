package com.leaderbord.dto.response;

import java.util.Arrays;
import java.util.List;

public class GroupsResponseDTO  {
    int groupId;
    String name;
    String[] gameTypes;
    List<SetsDTO> sets;

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

    public List<SetsDTO> getSets() {
        return sets;
    }

    public void setSets(List<SetsDTO> sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "GroupsResponseDTO{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", gameTypes=" + Arrays.toString(gameTypes) +
                ", sets=" + sets +
                '}';
    }
}
