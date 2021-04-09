package com.sanvalero.townleague.controller;

import com.sanvalero.townleague.domain.Player;
import com.sanvalero.townleague.domain.Response;
import com.sanvalero.townleague.domain.dto.PlayerDTO;
import com.sanvalero.townleague.exception.PlayerNotFoundException;
import com.sanvalero.townleague.service.PlayerService;
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

import static com.sanvalero.townleague.domain.Response.NOT_FOUND;

@RestController
@Tag(name = "Players", description = "Jugadores de la liga")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @Operation(summary = "Obtiene el listado de jugadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de jugadores",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Player.class))))
    })
    @GetMapping(value = "/players", produces = "application/json")
    public ResponseEntity<Set<Player>> getPlayers(){
        Set<Player> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un jugador determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el jugador",
                    content = @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "No existe el autor",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/players/{id}", produces = "application/json")
    public ResponseEntity<Player> getPlayer(@PathVariable long id){
        Player player = playerService.findById(id).orElseThrow(()-> new PlayerNotFoundException(id));
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo jugador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Jugadorregistrado",
                    content = @Content(schema = @Schema(implementation = Player.class))),
    })
    @PostMapping(value = "/players", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDTO playerDTO){
        Player addedPlayer = playerService.addPlayer(playerDTO);
        return new ResponseEntity<>(addedPlayer, HttpStatus.OK);
    }

    @Operation(summary = "Modifica un jugador determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugador modificado",
                    content = @Content(schema = @Schema(implementation = Player.class))),
            @ApiResponse(responseCode = "404", description = "No existe el autor",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/players/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Player> modifyPlayer(@PathVariable long id, @RequestBody Player newPlayer){
        Player player = playerService.modifyPlayer(id, newPlayer);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @Operation(summary = "Borra un jugador exitente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Jugadoreliminado",
                    content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "Jugador no encontrado",
                    content = @Content(schema = @Schema(implementation = Response.class)))
    })
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
