package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.domain.Team;
import com.sanvalero.townleague.domain.dto.TeamDTO;
import com.sanvalero.townleague.exception.TeamNotFoundException;
import com.sanvalero.townleague.service.TeamService;
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
@Tag(name = "Teams", description = "Equipos de la liga")
public class TeamController {

    private final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    TeamService teamService;

    @Operation(summary = "Obtiene un listado de los equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de equipos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Team.class))))
    })
    @GetMapping(value = "/teams", produces = "application/json")
    public ResponseEntity<Set<Team>> getTeams(){
        logger.info("init getTeams");
        Set<Team> teams = teamService.findAllTeams();
        logger.info("end getTeams");
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un equipo determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe equipo",
                    content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "No existe el equipo", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/teams/{id}", produces = "application/json")
    public ResponseEntity<Team> getTeam(@PathVariable long id){
        logger.info("init getTeam");
        Team team = teamService.findById(id).orElseThrow(()-> new TeamNotFoundException(id));
        logger.info("end getTeam");
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Equipo registrado",
                    content = @Content(schema = @Schema(implementation = Team.class))),
    })
    @PostMapping(value = "/teams", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> addTeam(@RequestBody TeamDTO teamDTO){
        logger.info("init addTeam");
        Team addedTeam = teamService.addTeam(teamDTO);
        logger.info("end addTeam");
        return new ResponseEntity<>(addedTeam, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un equipo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo modificado",
                    content = @Content(schema = @Schema(implementation = Team.class))),
            @ApiResponse(responseCode = "404", description = "No existe el equipo",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/teams/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Team> modifyTeam(@PathVariable long id,@RequestBody Team newTeam){
        logger.info("init modifyTeam");
        Team team = teamService.modifyTeam(id, newTeam);
        logger.info("end modifyTeam");
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
    }

    @Operation(summary = "Borra un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipo eliminado",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Equipo no encontrado",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/teams/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteTeam(@PathVariable long id){
        logger.info("init deleteTeam");
        teamService.deleteTeam(id);
        logger.info("end deleteTeam");
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    @ResponseStatus
    @ResponseBody
    public ResponseEntity<Response> handlerException(TeamNotFoundException tnfe){
        Response response = Response.errorResponse(NOT_FOUND, tnfe.getMessage());
        logger.error(tnfe.getMessage(), tnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
