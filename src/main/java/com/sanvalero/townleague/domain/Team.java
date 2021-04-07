package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column
    String name;

    @Column(name = "number_of_players")
    int numPlayers;

    @Column
    int points;

    @Column(name = "creation_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate creationDate;

    @Column(name = "is_last_champions")
    boolean isLastChampion;

    public Team(){
        players = new ArrayList<>();
    }

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    public void addPlayer(Player player){
        players.add(player);
    }
}
