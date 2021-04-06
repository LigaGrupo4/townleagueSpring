package com.sanvalero.townleague.repository;

import com.sanvalero.townleague.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    Set<Player> findAll();
}
