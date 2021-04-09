package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "referees")
public class Referee {

    @Schema(description = "Identificador del árbitro", example = "2")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del árbitro", example = "José")
    @Column
    private String name;

    @Schema(description = "Apellido del árbitro", example = "Jiménez")
    @Column(name = "last_name")
    private String lastName;

    @Schema(description = "Fecha de nacimiento del árbitro", example = "01/01/1990")
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @Schema(description = "Número de partidos asignados", example = "5")
    @Column(name = "number_of_matches")
    private int numMatches;

    @Schema(description = "Si el árbitro está disponible", example = "true")
    @Column
    private boolean available;

    @Schema(description = "Lista de partidos del árbitro")
    @OneToMany(mappedBy = "referee")
    @JsonBackReference
    private List<Match> matches;

    @Override
    public String toString() {
        return name + " " + lastName;
    }
}
