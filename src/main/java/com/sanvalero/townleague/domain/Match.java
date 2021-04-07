package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int leagueMatch;

    @Column
    private boolean suspended;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column
    private String record;

    public Match() {
        matchDetails = new ArrayList<>();
    }

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;

    @ManyToOne
    @JoinColumn(name = "referee_id")
    private Referee referee;

    @OneToMany(mappedBy = "match")
    private List<MatchDetail> matchDetails;

    public void addDetail(MatchDetail detail){
        matchDetails.add(detail);
    }
}
