package com.leaderboard.service;

import com.leaderboard.entity.Country;
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
    public void updatePlayer(Player newPlayer, Player currentPlayer) {
        String oldCode = currentPlayer.getCountry().getCode();
        String newCode = newPlayer.getCountry().getCode();
        if (!newCode.equals(oldCode)) {
            currentPlayer.setCountry(new Country(newCode));
            save(currentPlayer);
        }
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }
}
