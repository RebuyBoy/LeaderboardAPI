package com.leaderboard.service.interfaces;

import com.leaderboard.entity.Player;

import java.util.Optional;

public interface PlayerService {

    Optional<Player> getByName(String name);

    void updatePlayer(Player newPlayer, Player currentPlayer);

    Player save(Player player);

}
