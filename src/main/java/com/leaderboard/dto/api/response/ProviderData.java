package com.leaderboard.dto.api.response;

import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Stake;

import java.util.List;

public class ProviderData {

    private GameType gameType;
    private List<Stake> stakeList;

    public ProviderData(GameType gameType, List<Stake> stakeList) {
        this.gameType = gameType;
        this.stakeList = stakeList;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public List<Stake> getStakeList() {
        return stakeList;
    }

    @Override
    public String toString() {
        return "ProviderData{" +
                "gameType=" + gameType +
                ", stakeList=" + stakeList +
                '}';
    }

    public void setStakeList(List<Stake> stakeList) {
        this.stakeList = stakeList;
    }
}
