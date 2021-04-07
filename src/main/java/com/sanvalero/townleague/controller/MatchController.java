package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Match;
import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.exception.MatchNotFoundException;
import com.sanvalero.townleague.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;


    @GetMapping(value = "/matches", produces = "application/json")
    public ResponseEntity<Set<Match>> getMatches(){

      Set<Match> matches = matchService.findAll();
      return new ResponseEntity<>(matches, HttpStatus.OK);

    }


    @PostMapping(value = "/matches", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Match> addMatch(@RequestBody Match match) {
        Match addedMatch = matchService.addMatch(match);
        return new ResponseEntity<>(addedMatch, HttpStatus.OK);

    }

    @PutMapping(value = "/matches{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Match> modifyMatch(@PathVariable long id, @RequestBody Match newMatch) {
        Match match = matchService.modifyMatch(id, newMatch);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }


    @DeleteMapping(value = "/matches{id}", produces = "application/json")
    public ResponseEntity<Response> deleteMatch(@PathVariable long id){
        matchService.deleteMatch(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handlerException(MatchNotFoundException pnfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, pnfe.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
