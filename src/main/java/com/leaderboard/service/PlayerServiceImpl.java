package com.leaderboard.service;

import com.leaderboard.entity.Player;
import com.leaderboard.repository.PlayerRepository;
import com.leaderboard.service.interfaces.PlayerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Optional<Player> getByName(String name) {
        return playerRepository.getByName(name);
    }

    @Override
    public Player createIfNotExists(Player player) {
        Optional<Player> optionalPlayer = getByName(player.getName());
        return optionalPlayer.isEmpty()
                ? save(player)
                : optionalPlayer.get();
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
