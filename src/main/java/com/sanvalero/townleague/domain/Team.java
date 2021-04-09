package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "teams")
public class Team {

    @Schema(description = "Identificador del equipo", example = "5")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del equipo", example = "Osasuna")
    @Column
    private String name;

    @Schema(description = "Número de jugadores del equipo", example = "15")
    @Column(name = "number_of_players")
    private int numPlayers;

    @Schema(description = "Puntos del equipo", example = "12")
    @Column
    private int points;

    @Schema(description = "Fecha de alta del equipo", example = "09/04/2021")
    @Column(name = "creation_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    @Schema(description = "Si es el campeón de la liga anterior", example = "false")
    @Column(name = "is_last_champions")
    private boolean isLastChampion;

    public Team(){
        players = new ArrayList<>();
        matchDetails = new ArrayList<>();
    }

    @Schema(description = "Lista de jugadores del partido")
    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    @JsonBackReference
    private List<MatchDetail> matchDetails;

    public void addPlayer(Player player){
        players.add(player);
    }

    public void addDetail(MatchDetail detail){
        matchDetails.add(detail);
    }

    @Override
    public String toString() {
        return name;
    }
}
