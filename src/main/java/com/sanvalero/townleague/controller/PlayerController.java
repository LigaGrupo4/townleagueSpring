package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Player;
import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.domain.dto.PlayerDTO;
import com.sanvalero.townleague.exception.PlayerNotFoundException;
import com.sanvalero.townleague.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.townleague.domain.Response.NOT_FOUND;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping(value = "/players", produces = "application/json")
    public ResponseEntity<Set<Player>> getPlayers(){
        Set<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping(value = "/players/{id}", produces = "application/json")
    public ResponseEntity<Player> getPlayer(@PathVariable long id){
        Player player = playerService.findById(id).orElseThrow(()-> new PlayerNotFoundException(id));
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PostMapping(value = "/players", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDTO playerDTO){
        Player addedPlayer = playerService.addPlayer(playerDTO);
        return new ResponseEntity<>(addedPlayer, HttpStatus.OK);
    }

    @PutMapping(value = "/players/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Player> modifyPlayer(@PathVariable long id, @RequestBody Player newPlayer){
        Player player = playerService.modifyPlayer(id, newPlayer);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @DeleteMapping(value = "/players/{id}", produces = "application/json")
    public ResponseEntity<Response> deletePlayer(@PathVariable long id){
        playerService.deletePlayer(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus
    @ResponseBody
    public ResponseEntity<Response> handleException(PlayerNotFoundException pnfe){
        Response response = Response.errorResponse(NOT_FOUND, pnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
