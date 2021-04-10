package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.*;
import com.sanvalero.townleague.domain.dto.MatchDTO;
import com.sanvalero.townleague.domain.dto.ResultDTO;
import com.sanvalero.townleague.exception.*;
import com.sanvalero.townleague.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.sanvalero.townleague.Constants.LOCAL_CONDITION;
import static com.sanvalero.townleague.Constants.VISITOR_CONDITION;
import static com.sanvalero.townleague.domain.Response.BAD_REQUEST;

@Service
public class MatchServicelmpl implements MatchService {

    private final Logger logger = LoggerFactory.getLogger(MatchService.class);

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
        logger.info("init addMatch");
        Match newMatch = new Match();
        if (matchDTO.getLocalTeamName().equals("")
                || matchDTO.getVisitingTeamName().equals("")
                || matchDTO.getStadiumName().equals("")
                || matchDTO.getRefereeName().equals("")
                || matchDTO.getRefereeLastName().equals("")
                || matchDTO.getMatchDate() == null) {
            throw new MatchMandatoryFieldsException();
        }
        Referee referee = refereeRepository.findByNameAndLastName(matchDTO.getRefereeName(), matchDTO.getRefereeLastName())
                .orElseThrow(()->new RefereeNotFoundException(matchDTO.getRefereeName(), matchDTO.getRefereeLastName()));

        Stadium stadium = stadiumRepository.findByName(matchDTO.getStadiumName())
                .orElseThrow(()->new StadiumNotFoundException(matchDTO.getStadiumName()));

        logger.info("Stadium and Referee checked");

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

        logger.info("Teams checked");

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

        logger.info("end addMatch");
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
        Match match = matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException(id));
        List<MatchDetail> matchDetails = match.getMatchDetails();
        for(MatchDetail matchDetail : matchDetails){
            long detailId = matchDetail.getId();
            matchDetailRepository.deleteById(detailId);
        }
        matchRepository.deleteById(id);
    }

    @Override
    public void insertResult(long id, ResultDTO resultDTO) {
        Match match = matchRepository.findById(id).orElseThrow(()-> new MatchNotFoundException(id));
        List<MatchDetail> matchDetails = match.getMatchDetails();
        Team localTeam = null;
        Team visitingTeam = null;
        for(MatchDetail matchDetail : matchDetails){
            if (matchDetail.getCondition().equals(LOCAL_CONDITION)){
                matchDetail.setGoals(resultDTO.getLocalGoals());
                localTeam = matchDetail.getTeam();
            }else {
                matchDetail.setGoals(resultDTO.getVisitorGoals());
                visitingTeam = matchDetail.getTeam();
            }
            matchDetailRepository.save(matchDetail);
        }

        int pointsLocalTeam = localTeam.getPoints();
        int pointsVisitingTeam = visitingTeam.getPoints();

        if(resultDTO.getLocalGoals()>resultDTO.getVisitorGoals()){
            localTeam.setPoints(pointsLocalTeam+3);
        } else if(resultDTO.getLocalGoals()<resultDTO.getVisitorGoals()){
            visitingTeam.setPoints(pointsVisitingTeam+3);
        } else {
            localTeam.setPoints(pointsLocalTeam+1);
            visitingTeam.setPoints(pointsVisitingTeam+1);
        }

        teamRepository.save(localTeam);
        teamRepository.save(visitingTeam);
    }

    @ExceptionHandler(MatchMandatoryFieldsException.class)
    @ResponseStatus
    @ResponseBody
    public ResponseEntity<Response> handlerException(MatchMandatoryFieldsException mmfe){
        Response response = Response.errorResponse(BAD_REQUEST, mmfe.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
