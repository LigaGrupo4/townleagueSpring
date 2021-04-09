package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.domain.Stadium;
import com.sanvalero.townleague.exception.MatchNotFoundException;
import com.sanvalero.townleague.exception.StadiumNotFoundException;
import com.sanvalero.townleague.service.StadiumService;
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

@RestController
@Tag(name = "Stadiums", description = "Estadios de la liga")
public class StadiumController {

    private final Logger logger = LoggerFactory.getLogger(StadiumController.class);

    @Autowired
    StadiumService stadiumService;

    @Operation(summary = "Obtiene el listado de estadios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de estadios",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Stadium.class))))
    })
    @GetMapping(value = "/stadiums", produces = "application/json")
    public ResponseEntity<Set<Stadium>> getStadiums(){
        logger.info("init getStadiums");
        Set<Stadium> stadiums = stadiumService.findAllStadiums();
        logger.info("end getStadiums");
        return new ResponseEntity<>(stadiums, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un estadio determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el estadio",
                    content = @Content(schema = @Schema(implementation = Stadium.class))),
            @ApiResponse(responseCode = "404", description = "No existe el estadio",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/stadiums/{id}", produces = "application/json")
    public ResponseEntity<Stadium> getStadium(long id){
        logger.info("init getStadium");
        Stadium stadium = stadiumService.findById(id).orElseThrow(()->new StadiumNotFoundException(id));
        logger.info("end getStadium");
        return new ResponseEntity<>(stadium, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo estadio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estadio registrado",
                    content = @Content(schema = @Schema(implementation = Stadium.class))),
    })
    @PostMapping(value = "/stadiums", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Stadium> addStadium(@RequestBody Stadium stadium) {
        logger.info("init addStadium");
        Stadium addedStadiums = stadiumService.addStadium(stadium);
        logger.info("end addStadium");
     return new ResponseEntity<>(addedStadiums, HttpStatus.OK);

    }

    @Operation(summary = "Modifica un estadio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadio modificado",
                    content = @Content(schema = @Schema(implementation = Stadium.class))),
            @ApiResponse(responseCode = "404", description = "No existe el autor",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/stadiums/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Stadium> modifyStadium(@PathVariable long id, @RequestBody Stadium newStadium) {
        logger.info("init modifyStadium");
        Stadium stadium = stadiumService.modifyStadium(id, newStadium);
        logger.info("end modifyStadium");
        return new ResponseEntity<>(newStadium, HttpStatus.OK);
    }

    @Operation(summary = "Borra un estadio exitente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estadio eliminado",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Estadio no encontrado",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/stadiums/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteStadium(@PathVariable long id){
        logger.info("init deleteStadium");
        stadiumService.deleteStadium(id);
        logger.info("end deleteStadium");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(StadiumNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handlerException(MatchNotFoundException snfe){
        Response response = Response.errorResponse(Response.NOT_FOUND, snfe.getMessage());
        logger.error(snfe.getMessage(), snfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
