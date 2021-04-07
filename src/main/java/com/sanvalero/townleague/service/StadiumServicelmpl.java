package com.sanvalero.townleague.service;

import com.sanvalero.townleague.domain.Stadium;
import com.sanvalero.townleague.exception.StadiumNotFoundException;
import com.sanvalero.townleague.repository.StadiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StadiumServicelmpl implements StadiumService{

    @Autowired
    StadiumRepository stadiumRepository;

    @Override
    public Set<Stadium> findAllStadiums() {
        return stadiumRepository.findAll();
    }

    @Override
    public Optional<Stadium> findById(long id) {
        return stadiumRepository.findById(id);
    }

    @Override
    public Stadium addStadium(Stadium stadium) {
        return stadiumRepository.save(stadium);
    }

    @Override
    public Stadium modifyStadium(long id, Stadium newStadium) {
        Stadium stadiums = stadiumRepository.findById(id).orElseThrow(() -> new StadiumNotFoundException(id));
        newStadium.setId(stadiums.getId());
        return stadiumRepository.save(newStadium);
    }

    @Override
    public void deleteStadium(long id) {
        stadiumRepository.findById(id).orElseThrow(() -> new StadiumNotFoundException(id));
        stadiumRepository.deleteById(id);
    }
}
