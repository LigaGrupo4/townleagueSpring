package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Player;
import com.sanvalero.townleague.domain.Team;
import com.sanvalero.townleague.domain.dto.PlayerDTO;
import com.sanvalero.townleague.domain.dto.TeamDTO;
import com.sanvalero.townleague.exception.TeamNotFoundException;
import com.sanvalero.townleague.repository.PlayerRepository;
import com.sanvalero.townleague.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImp implements TeamService{

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Set<Team> findAllTeams() {
        return teamRepository.findAllOrderByPointsDesc();
    }

    @Override
    public Optional<Team> findById(long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Team addTeam(TeamDTO teamDTO) {
        Team newTeam = new Team();
        newTeam.setName(teamDTO.getTeamName());
        newTeam.setCreationDate(LocalDate.now());
        int numPlayers = 0;
        newTeam = teamRepository.save(newTeam);

        for(PlayerDTO playerDTO : teamDTO.getPlayersList()){
            Player newPlayer = new Player();
            newPlayer.setName(playerDTO.getPlayerName());
            newPlayer.setLastName(playerDTO.getPlayerLastName());
            newPlayer.setBirthday(playerDTO.getPlayerBirthday());
            newPlayer.setTeam(newTeam);
            newPlayer = playerRepository.save(newPlayer);

            newTeam.addPlayer(newPlayer);
            numPlayers++;
            newTeam.setNumPlayers(numPlayers);
        }

        return teamRepository.save(newTeam);
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
