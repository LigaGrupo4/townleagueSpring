package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Team;

import java.util.Optional;
import java.util.Set;

public interface TeamService {

    Set<Team> findAllTeams();
    Optional<Team> findById(long id);
    Team addTeam(Team team);
    Team modifyTeam(long id, Team team);
    void deleteTeam(long id);
}
