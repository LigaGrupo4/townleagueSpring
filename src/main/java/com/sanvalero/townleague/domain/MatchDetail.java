package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "match_details")
public class MatchDetail {

    @Schema(description = "Identifiacdor del detalle de partido", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Número de goles del equipo", example = "0")
    @Column
    private int goals;

    @Schema(description = "Condición de visitnate o local del equipo", example = "local")
    @Column
    private String condition;

    @Schema(description = "Partido al que pertenece el detalle")
    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private Match match;

    @Schema(description = "Equipo al que pertenece el detalle")
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
