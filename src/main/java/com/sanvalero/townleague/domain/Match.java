package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Entity(name = "matches")
public class Match {

    @Schema(description = "Identificador del partido", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Jornada a la que pertenece el partido", example = "1")
    @Column
    private int leagueMatch;

    @Schema(description = "Indicador si el partido ha sido suspendido", example = "true")
    @Column
    private boolean suspended;

    @Schema(description = "Fecha en la que se disputa el partido", example = "08/04/2021")
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Schema(description = "Acta del partido", example = "Partido jugado sin incidencias")
    @Column
    private String record;

    public Match() {
        matchDetails = new ArrayList<>();
    }

    @Schema(description = "Estadio donde se diputa el partido")
    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @Schema(description = "Árbitro del partido")
    @ManyToOne
    @JoinColumn(name = "referee_id")
    private Referee referee;

    @Schema(description = "Detalles del partido")
    @OneToMany(mappedBy = "match")
    private List<MatchDetail> matchDetails;

    public void addDetail(MatchDetail detail){
        matchDetails.add(detail);
    }
}
