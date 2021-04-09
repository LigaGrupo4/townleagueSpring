package com.sanvalero.townleague.domain.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamDTO {

    @Schema(description = "Nombre del equipo")
    private String teamName;

    @Schema(description = "Lista de jugadores del equipo")
    private List<PlayerDTO> playersList;
}
