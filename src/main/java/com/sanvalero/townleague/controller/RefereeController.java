package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Referee;
import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.exception.RefereeNotFoundException;
import com.sanvalero.townleague.service.RefereeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.townleague.domain.Response.NOT_FOUND;

@RestController
@Tag(name = "Referees", description = "Árbitros de la liga")
public class RefereeController {

    private final Logger logger = LoggerFactory.getLogger(RefereeController.class);

    @Autowired
    RefereeService refereeService;

    @Operation(summary = "Obtiene el listado de árbitros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de árbitros",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Referee.class))))
    })
    @GetMapping(value = "/referees", produces = "application/json")
    public ResponseEntity<Set<Referee>> getReferees(){
        logger.info("init getReferees");
        Set<Referee> referees = refereeService.findAllReferees();
        logger.info("end getReferees");
        return new ResponseEntity<>(referees, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un árbitro determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el árbitro",
                    content = @Content(schema = @Schema(implementation = Referee.class))),
            @ApiResponse(responseCode = "404", description = "No existe el árbitro",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/referees/{id}", produces = "application/json")
    public ResponseEntity<Referee> getReferee(@PathVariable long id){
        logger.info("init getReferee");
        Referee referee = refereeService.findById(id).orElseThrow(()->new RefereeNotFoundException(id));
        logger.info("end getReferee");
        return new ResponseEntity<>(referee, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo árbitro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Árbitro registrado",
                    content = @Content(schema = @Schema(implementation = Referee.class))),
    })
    @PostMapping(value = "/referees", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Referee> addReferee(@RequestBody Referee referee){
        logger.info("init addReferee");
        Referee addedReferee = refereeService.addReferee(referee);
        logger.info("end addReferee");
        return new ResponseEntity<>(addedReferee, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un árbitro existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Árbitro modificado",
                    content = @Content(schema = @Schema(implementation = Referee.class))),
            @ApiResponse(responseCode = "404", description = "No existe el árbitro",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/referees/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Referee> modifyReferee(@PathVariable long id, @RequestBody Referee newReferee){
        logger.info("init modifyReferee");
        Referee referee = refereeService.modifyReferee(id, newReferee);
        logger.info("end modifyReferee");
        return new ResponseEntity<>(referee, HttpStatus.OK);
    }

    @Operation(summary = "Borra un árbitro exitente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Árbitro eliminado",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Árbitro no encontrado",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/referees/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteReferee(@PathVariable long id){
        logger.info("init deleteReferee");
        refereeService.deleteReferee(id);
        logger.info("end deleteReferee");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }


    @ExceptionHandler(RefereeNotFoundException.class)
    @ResponseStatus
    @ResponseBody
    public ResponseEntity<Response> handlerException(RefereeNotFoundException rnfe){
        Response response = Response.errorResponse(NOT_FOUND, rnfe.getMessage());
        logger.error(rnfe.getMessage(), rnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
