package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Match;
import com.sanvalero.townleague.exception.MatchNotFoundException;
import com.sanvalero.townleague.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MatchServicelmpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Override
    public Set<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Match findByLeagueMatch(int leagueMatch) {
        return matchRepository.findByLeagueMatch(leagueMatch);
    }

    @Override
    public Optional<Match> findById(long id) {
        return matchRepository.findById(id);
    }

    @Override
    public Match addMatch(Match match) {
        return matchRepository.save(match);
    }

    @Override
    public Match modifyMatch(long id, Match newMatch) {
        Match match = matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException(id));
        newMatch.setId(match.getId());
        return matchRepository.save(newMatch);
    }

    @Override
    public void deleteMatch(long id) {
    matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException(id));
    matchRepository.deleteById(id);
    }
}
