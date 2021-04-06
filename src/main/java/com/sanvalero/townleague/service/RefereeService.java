package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Referee;

import java.util.Optional;
import java.util.Set;

public interface RefereeService {

    Set<Referee> findAllReferees();
    Optional<Referee> findById(long id);
    Referee addReferee(Referee referee);
    Referee modifyReferee(long id, Referee referee);
    void deleteReferee(long id);
}
