package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Match;
import com.sanvalero.townleague.domain.dto.MatchDTO;
import com.sanvalero.townleague.domain.dto.ResultDTO;

import java.util.Optional;
import java.util.Set;

public interface MatchService {

    Set<Match> findAll();
    Match findByLeagueMatch(int leagueMatch);
    Optional<Match> findById(long id);
    Match addMatch(MatchDTO matchDTO);
    Match modifyMatch(long id, Match newMatch);
    void deleteMatch(long id);
    void insertResult(long id, ResultDTO resultDTO);
}
