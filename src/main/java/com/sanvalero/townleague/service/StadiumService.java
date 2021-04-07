package com.sanvalero.townleague.service;


import com.sanvalero.townleague.domain.Stadium;

import java.util.Optional;
import java.util.Set;

public interface StadiumService {

Set<Stadium> findAllStadiums();
Optional<Stadium> findById(long id);
Stadium addStadium(Stadium stadium);
Stadium modifyStadium(long id, Stadium stadium);
void deleteStadium(long id);



}
