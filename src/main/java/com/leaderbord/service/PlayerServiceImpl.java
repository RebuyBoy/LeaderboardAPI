package com.leaderbord.service;

import com.leaderbord.entity.Player;
import com.leaderbord.repository.PlayerRepository;
import com.leaderbord.service.interfaces.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player getByName(String name) {
        return playerRepository.getByName(name);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
