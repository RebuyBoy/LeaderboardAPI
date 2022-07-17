package com.leaderbord.service.interfaces;

import com.leaderbord.entity.Player;

public interface PlayerService {
    Player getByName(String name);
    Player save(Player player);
}
