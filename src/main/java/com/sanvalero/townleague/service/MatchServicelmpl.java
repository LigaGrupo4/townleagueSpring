package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.*;
import com.sanvalero.townleague.domain.dto.MatchDTO;
import com.sanvalero.townleague.exception.MatchNotFoundException;
import com.sanvalero.townleague.exception.RefereeNotFoundException;
import com.sanvalero.townleague.exception.StadiumNotFoundException;
import com.sanvalero.townleague.exception.TeamNotFoundException;
import com.sanvalero.townleague.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

import static com.sanvalero.townleague.Constants.LOCAL_CONDITION;
import static com.sanvalero.townleague.Constants.VISITOR_CONDITION;

@Service
public class MatchServicelmpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RefereeRepository refereeRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchDetailRepository matchDetailRepository;

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
    public Match addMatch(MatchDTO matchDTO) {
        Match newMatch = new Match();

        Referee referee = refereeRepository.findByNameAndLastName(matchDTO.getRefereeName(), matchDTO.getRefereeLastName())
                .orElseThrow(()->new RefereeNotFoundException(matchDTO.getRefereeName(), matchDTO.getRefereeLastName()));

        Stadium stadium = stadiumRepository.findByName(matchDTO.getStadiumName())
                .orElseThrow(()->new StadiumNotFoundException(matchDTO.getStadiumName()));

        newMatch.setDate(matchDTO.getMatchDate());
        newMatch.setReferee(referee);
        newMatch.setStadium(stadium);
        newMatch = matchRepository.save(newMatch);

        MatchDetail localMatchDetail = new MatchDetail();
        MatchDetail visitingMatchDetail = new MatchDetail();

        Team localTeam = teamRepository.findByName(matchDTO.getLocalTeamName())
                .orElseThrow(()->new TeamNotFoundException(matchDTO.getLocalTeamName()));

        Team visitingTeam = teamRepository.findByName(matchDTO.getVisitingTeamName())
                .orElseThrow(()->new TeamNotFoundException(matchDTO.getVisitingTeamName()));


        localMatchDetail.setMatch(newMatch);
        localMatchDetail.setTeam(localTeam);
        localMatchDetail.setCondition(LOCAL_CONDITION);

        visitingMatchDetail.setMatch(newMatch);
        visitingMatchDetail.setTeam(visitingTeam);
        visitingMatchDetail.setCondition(VISITOR_CONDITION);

        localMatchDetail = matchDetailRepository.save(localMatchDetail);
        visitingMatchDetail = matchDetailRepository.save(visitingMatchDetail);

        localTeam.addDetail(localMatchDetail);
        visitingTeam.addDetail(visitingMatchDetail);
        newMatch.addDetail(localMatchDetail);
        newMatch.addDetail(visitingMatchDetail);

        return matchRepository.save(newMatch);
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
