package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Match;
import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.domain.dto.MatchDTO;
import com.sanvalero.townleague.exception.MatchNotFoundException;
import com.sanvalero.townleague.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Matches", description = "Partidos de la liga")
public class MatchController {

    @Autowired
    MatchService matchService;

    @Operation(summary = "Obtiene el listado de partidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de partidos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Match.class))))
    })
    @GetMapping(value = "/matches", produces = "application/json")
    public ResponseEntity<Set<Match>> getMatches(){
      Set<Match> matches = matchService.findAll();
      return new ResponseEntity<>(matches, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un partido determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el partido",
                    content = @Content(schema = @Schema(implementation = Match.class))),
            @ApiResponse(responseCode = "404", description = "No existe el autor",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/matches/{id}", produces = "application/json")
    public ResponseEntity<Match> getMatch(long id){
        Match match = matchService.findById(id).orElseThrow(()->new MatchNotFoundException(id));
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Partido registrado",
                    content = @Content(schema = @Schema(implementation = Match.class))),
            @ApiResponse(responseCode = "404", description = "No existe Equipo/Árbitro/Estadio",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PostMapping(value = "/matches", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Match> addMatch(@RequestBody MatchDTO matchDTO) {
        Match addedMatch = matchService.addMatch(matchDTO);
        return new ResponseEntity<>(addedMatch, HttpStatus.OK);

    }

    @Operation(summary = "Modifica un partido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Partido modificado",
                    content = @Content(schema = @Schema(implementation = Match.class))),
            @ApiResponse(responseCode = "404", description = "No existe el partido",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/matches/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Match> modifyMatch(@PathVariable long id, @RequestBody Match newMatch) {
        Match match = matchService.modifyMatch(id, newMatch);
        return new ResponseEntity<>(match, HttpStatus.OK);
    }

    @Operation(summary = "Borra un partido exitente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Partido eliminado",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Partido no encontrado",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/matches/{id}", produces = "application/json")
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
