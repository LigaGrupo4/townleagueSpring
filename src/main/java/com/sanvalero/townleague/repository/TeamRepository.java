package com.sanvalero.townleague.repository;

import com.sanvalero.townleague.domain.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Set<Team> findAllOrderByPointsDesc();
    Optional<Team> findByName(String name);
}
