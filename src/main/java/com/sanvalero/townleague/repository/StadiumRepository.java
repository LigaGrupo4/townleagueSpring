package com.sanvalero.townleague.repository;

import com.sanvalero.townleague.domain.Stadium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StadiumRepository extends CrudRepository<Stadium, Long>{
    Set<Stadium> findAll();
}
