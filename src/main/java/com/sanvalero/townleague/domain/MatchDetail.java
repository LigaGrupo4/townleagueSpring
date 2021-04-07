package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "match_details")
public class MatchDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int goals;

    @Column
    private String condition;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private Match match;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
