package com.sanvalero.townleague.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PlayerDTO {

    @Schema(description = "Nombre deel jugador", example = "Javier")
    private String playerName;

    @Schema(description = "Apellido del jugador", example = "Fernández")
    private String playerLastName;

    @Schema(description = "Fecha de nacimiento del jugador", example = "05/05/1995")
    private LocalDate playerBirthday;
}
