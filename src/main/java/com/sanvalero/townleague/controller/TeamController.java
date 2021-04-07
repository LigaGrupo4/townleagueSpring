package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.domain.Team;
import com.sanvalero.townleague.domain.dto.TeamDTO;
import com.sanvalero.townleague.exception.TeamNotFoundException;
import com.sanvalero.townleague.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.townleague.domain.Response.NOT_FOUND;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping(value = "/teams", produces = "application/json")
    public ResponseEntity<Set<Team>> getTeams(){
        Set<Team> teams = teamService.findAllTeams();
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @GetMapping(value = "/teams/{id}", produces = "application/json")
    public ResponseEntity<Team> getTeam(@PathVariable long id){
        Team team = teamService.findById(id).orElseThrow(()-> new TeamNotFoundException(id));
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PostMapping(value = "/teams", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> addTeam(@RequestBody TeamDTO teamDTO){
        Team addedTeam = teamService.addTeam(teamDTO);
        return new ResponseEntity<>(addedTeam, HttpStatus.CREATED);
    }

    @PutMapping(value = "/teams/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> modifyTeam(@PathVariable long id,@RequestBody Team newTeam){
        Team team = teamService.modifyTeam(id, newTeam);
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
    }

    @DeleteMapping(value = "/teams/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteTeam(@PathVariable long id){
        teamService.deleteTeam(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus
    @ResponseBody
    public ResponseEntity<Response> handlerException(TeamNotFoundException tnfe){
        Response response = Response.errorResponse(NOT_FOUND, tnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
