package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Player;
import com.sanvalero.townleague.domain.dto.PlayerDTO;

import java.util.Optional;
import java.util.Set;

public interface PlayerService {

    Set<Player> findAllPlayers();
    Optional<Player> findById(long id);
    Player addPlayer(PlayerDTO playerDTO);
    Player modifyPlayer(long id, Player player);
    void deletePlayer(long id);
}
