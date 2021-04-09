package com.sanvalero.townleague.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "stadiums")
public class Stadium {

    @Schema(description = "Identificador del estadio", example = "3")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Aforo del estadio", example = "500")
    @Column
    private int capacity;

    @Schema(description = "Nombre del estadio", example = "La Alameda")
    @Column
    private String name;

    @Schema(description = "Si el estadio está disponible", example = "true")
    @Column
    private boolean available;

    @Schema(description = "Fecha de construcción del estadio", example = "01/01/1980")
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateConstruction;

    @Schema(description = "Direccion del estadio", example = "Calle Violeta Parra, 9")
    @Column
    private String direction;

    @Schema(description = "Listad de partidos del estadio")
    @OneToMany(mappedBy = "stadium")
    @JsonBackReference
    private List<Match> matches;

    @Override
    public String toString() {
        return name;
    }
}
