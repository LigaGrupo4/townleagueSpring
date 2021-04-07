package com.sanvalero.townleague.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MatchDTO {

    private String localTeamName;
    private String visitingTeamName;
    private String stadiumName;
    private String refereeName;
    private String refereeLastName;
    private LocalDate matchDate;
}
