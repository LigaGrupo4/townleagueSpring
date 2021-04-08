package com.sanvalero.townleague.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "referees")
public class Referee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @Column(name = "number_of_matches")
    private int numMatches;

    @Column
    private boolean available;

    @OneToMany(mappedBy = "referee")
    @JsonBackReference
    private List<Match> matches;

    @Override
    public String toString() {
        return name + " " + lastName;
    }
}
