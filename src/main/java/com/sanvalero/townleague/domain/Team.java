package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private long id;

    @Column
    private String name;

    @Column(name = "number_of_players")
    private int numPlayers;

    @Column
    private int points;

    @Column(name = "creation_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate creationDate;

    @Column(name = "is_last_champions")
    private boolean isLastChampion;

    public Team(){
        players = new ArrayList<>();
        matchDetails = new ArrayList<>();
    }

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
