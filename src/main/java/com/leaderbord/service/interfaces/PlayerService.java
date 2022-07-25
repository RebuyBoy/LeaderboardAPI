package com.leaderbord.service.interfaces;

import com.leaderbord.entity.Player;

import java.util.Optional;

public interface PlayerService {
    Optional<Player> getByName(String name);
    Player createIfNotExists(Player player);
    Player save(Player player);
}
