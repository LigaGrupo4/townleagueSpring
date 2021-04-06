package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int goals;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @Column
    private boolean sanctioned;
}
