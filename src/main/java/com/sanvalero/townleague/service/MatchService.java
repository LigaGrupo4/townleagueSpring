package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Match;

import java.util.Optional;
import java.util.Set;

public interface MatchService {

    Set<Match> findAll();
    Match findByLeagueMatch(int leagueMatch);

    Optional<Match> findById(long id);
    Match addMatch(Match match);
    Match modifyMatch(long id, Match newMatch);
    void deleteMatch(long id);
}
