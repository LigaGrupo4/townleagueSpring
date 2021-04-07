package com.sanvalero.townleague.domain.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamDTO {

    private String teamName;
    private List<PlayerDTO> playersList;

}
