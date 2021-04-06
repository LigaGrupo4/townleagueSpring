package com.sanvalero.townleague.repository;

import com.sanvalero.townleague.domain.Referee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RefereeRepository extends CrudRepository<Referee, Long> {

    Set<Referee> findAll();
}
