package com.leaderboard.service.interfaces;

import com.leaderboard.entity.Player;

import java.util.Optional;

public interface PlayerService {
    Optional<Player> getByName(String name);
    Player createIfNotExists(Player player);
    Player save(Player player);
}
