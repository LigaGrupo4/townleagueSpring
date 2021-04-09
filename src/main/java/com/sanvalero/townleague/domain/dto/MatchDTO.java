package com.sanvalero.townleague.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MatchDTO {

    @Schema(description = "Nombre del equipo local", example = "Equipo 1")
    private String localTeamName;

    @Schema(description = "Nombre del equipo visitante", example = "Equipo 2")
    private String visitingTeamName;

    @Schema(description = "Nombre del estadio", example = "Estadio 1")
    private String stadiumName;

    @Schema(description = "Nombre del árbitro", example = "Juan")
    private String refereeName;

    @Schema(description = "Apellido del árbitro", example = "García")
    private String refereeLastName;

    @Schema(description = "Fefcha del partido", example = "10/04/2021")
    private LocalDate matchDate;
}
