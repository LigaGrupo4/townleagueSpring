package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
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



}
