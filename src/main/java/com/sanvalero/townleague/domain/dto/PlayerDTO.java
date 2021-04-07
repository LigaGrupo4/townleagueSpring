package com.sanvalero.townleague.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PlayerDTO {

    private String playerName;
    private String playerLastName;
    private LocalDate playerBirthday;
}
