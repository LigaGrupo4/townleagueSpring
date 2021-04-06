package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Referee;
import com.sanvalero.townleague.exception.RefereeNotFoundException;
import com.sanvalero.townleague.repository.RefereeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RefereeServiceImp implements RefereeService{

    @Autowired
    RefereeRepository refereeRepository;

    @Override
    public Set<Referee> findAllReferees() {
        return refereeRepository.findAll();
    }

    @Override
    public Optional<Referee> findById(long id) {
        return refereeRepository.findById(id);
    }

    @Override
    public Referee addReferee(Referee referee) {
        return refereeRepository.save(referee);
    }

    @Override
    public Referee modifyReferee(long id, Referee newReferee) {
        Referee referee = refereeRepository.findById(id).orElseThrow(()->new RefereeNotFoundException(id));
        newReferee.setId(referee.getId());
        return refereeRepository.save(newReferee);
    }

    @Override
    public void deleteReferee(long id) {
        refereeRepository.findById(id).orElseThrow(()->new RefereeNotFoundException(id));
        refereeRepository.deleteById(id);
    }
}
