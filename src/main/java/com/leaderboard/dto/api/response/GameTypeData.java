package com.leaderboard.dto.api.response;

import com.leaderboard.entity.GameType;
import com.leaderboard.entity.Stake;

import java.util.List;

public class GameTypeData {
    private GameType gameType;
    private List<Stake> stakeList;

    public List<Stake> getStakeList() {
        return stakeList;
    }

    public void setStakeList(List<Stake> stakeList) {
        this.stakeList = stakeList;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    @Override
    public String toString() {
        return "GameTypeData{" +
                "gameType=" + gameType +
                ", stakeList=" + stakeList +
                '}';
    }
}
