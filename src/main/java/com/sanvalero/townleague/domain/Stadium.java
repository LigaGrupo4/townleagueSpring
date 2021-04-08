package com.sanvalero.townleague.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int capacity;

    @Column
    private String name;

    @Column
    private boolean available;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateConstruction;

    @Column
    private String direction;

    @OneToMany(mappedBy = "stadium")
    @JsonBackReference
    private List<Match> matches;

    @Override
    public String toString() {
        return name;
    }
}
