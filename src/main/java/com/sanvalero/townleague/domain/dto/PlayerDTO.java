package com.sanvalero.townleague.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate playerBirthday;
}
