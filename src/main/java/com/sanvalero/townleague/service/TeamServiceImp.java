package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Team;
import com.sanvalero.townleague.exception.TeamNotFoundException;
import com.sanvalero.townleague.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImp implements TeamService{

    @Autowired
    TeamRepository teamRepository;

    @Override
    public Set<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team modifyTeam(long id, Team newTeam) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new TeamNotFoundException(id));
        newTeam.setId(team.getId());
        return teamRepository.save(newTeam);
    }

    @Override
    public void deleteTeam(long id) {
        teamRepository.findById(id).orElseThrow(()-> new TeamNotFoundException(id));
        teamRepository.deleteById(id);
    }
}
