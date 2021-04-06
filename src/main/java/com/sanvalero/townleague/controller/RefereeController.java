package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Referee;
import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.exception.RefereeNotFoundException;
import com.sanvalero.townleague.service.RefereeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.townleague.domain.Response.NOT_FOUND;

@RestController
public class RefereeController {

    @Autowired
    RefereeService refereeService;

    @GetMapping(value = "/referees", produces = "application/json")
    public ResponseEntity<Set<Referee>> getReferees(){
        Set<Referee> referees = refereeService.findAllReferees();
        return new ResponseEntity<>(referees, HttpStatus.OK);
    }

    @GetMapping(value = "/referees/{id}", produces = "application/json")
    public ResponseEntity<Referee> getReferee(@PathVariable long id){
        Referee referee = refereeService.findById(id).orElseThrow(()->new RefereeNotFoundException(id));
        return new ResponseEntity<>(referee, HttpStatus.OK);
    }

    @PostMapping(value = "/referees", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Referee> addReferee(@RequestBody Referee referee){
        Referee addedReferee = refereeService.addReferee(referee);
        return new ResponseEntity<>(addedReferee, HttpStatus.CREATED);
    }

    @PutMapping(value = "/referees/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Referee> modifyReferee(@PathVariable long id, @RequestBody Referee newReferee){
        Referee referee = refereeService.modifyReferee(id, newReferee);
        return new ResponseEntity<>(referee, HttpStatus.OK);
    }

    @DeleteMapping(value = "/referees/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteReferee(@PathVariable long id){
        refereeService.deleteReferee(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }


    @ExceptionHandler(RefereeNotFoundException.class)
    @ResponseStatus
    @ResponseBody
    public ResponseEntity<Response> handlerException(RefereeNotFoundException rnfe){
        Response response = Response.errorResponse(NOT_FOUND, rnfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
