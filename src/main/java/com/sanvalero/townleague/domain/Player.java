package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "players")
public class Player {

    @Schema(description = "Identificador del jugador", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del jugador", example = "Sebastián")
    @Column
    private String name;

    @Schema(description = "Apellido del jugador", example = "Iribarren")
    @Column(name = "last_name")
    private String lastName;

    @Schema(description = "Numero de goles del jugador", example = "16")
    @Column
    private int goals;

    @Schema(description = "Fecha de nacimiento del jugador", example = "01/01/2000")
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @Schema(description = "Si el jugador esta sancionado o no", example = "false")
    @Column
    private boolean sanctioned;

    @Schema(description = "Equipo del jugador")
    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;
}
