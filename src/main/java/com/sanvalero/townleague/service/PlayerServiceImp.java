package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Player;
import com.sanvalero.townleague.exception.PlayerNotFoundException;
import com.sanvalero.townleague.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlayerServiceImp implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Set<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findById(long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Player modifyPlayer(long id, Player newPlayer) {
        Player player = playerRepository.findById(id).orElseThrow(()-> new PlayerNotFoundException(id));
        newPlayer.setId(player.getId());
        return playerRepository.save(newPlayer);
    }

    @Override
    public void deletePlayer(long id) {
        playerRepository.findById(id).orElseThrow(()->new PlayerNotFoundException(id));
        playerRepository.deleteById(id);
    }
}
