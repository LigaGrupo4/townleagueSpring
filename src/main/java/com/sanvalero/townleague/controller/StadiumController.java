package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.domain.Stadium;
import com.sanvalero.townleague.exception.MatchNotFoundException;
import com.sanvalero.townleague.exception.StadiumNotFoundException;
import com.sanvalero.townleague.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class StadiumController {

    @Autowired
    StadiumService stadiumService;



    @GetMapping(value = "/stadiums", produces = "application/json")
    public ResponseEntity<Set<Stadium>> getStadiums(){

        Set<Stadium> stadiums = stadiumService.findAllStadiums();
        return new ResponseEntity<>(stadiums, HttpStatus.OK);

    }


    @PostMapping(value = "/stadiums", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Stadium> addStadium(@RequestBody Stadium stadium) {
        Stadium addedStadiums = stadiumService.addStadium(stadium);
     return new ResponseEntity<>(addedStadiums, HttpStatus.OK);

    }

    @PutMapping(value = "/stadiums/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Stadium> modifyStadium(@PathVariable long id, @RequestBody Stadium newStadium) {
        Stadium stadium = stadiumService.modifyStadium(id, newStadium);
        return new ResponseEntity<>(newStadium, HttpStatus.OK);
    }

    @DeleteMapping(value = "/stadiums/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteStadium(@PathVariable long id){
        stadiumService.deleteStadium(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(StadiumNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handlerException(MatchNotFoundException pnfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, pnfe.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
