package com.leaderboard.dto.api.response;

import com.leaderboard.entity.GameType;

import java.util.List;

public class ProviderData {

    private GameType gameType;
    private List<StakeResponse> stakeList;

    public ProviderData(GameType gameType, List<StakeResponse> stakeList) {
        this.gameType = gameType;
        this.stakeList = stakeList;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public List<StakeResponse> getStakeList() {
        return stakeList;
    }

    public void setStakeList(List<StakeResponse> stakeList) {
        this.stakeList = stakeList;
    }

    @Override
    public String toString() {
        return "ProviderData{" +
                "gameType=" + gameType +
                ", stakeList=" + stakeList +
                '}';
    }

}
