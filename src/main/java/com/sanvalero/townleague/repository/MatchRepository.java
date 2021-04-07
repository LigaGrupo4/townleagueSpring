package com.sanvalero.townleague.repository;

import com.sanvalero.townleague.domain.Match;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long> {

Set<Match> findAll();
Match findByLeagueMatch(int leagueMatch);

}
