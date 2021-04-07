package com.sanvalero.townleague.repository;

import com.sanvalero.townleague.domain.MatchDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDetailRepository extends CrudRepository<MatchDetail, Long> {

}
