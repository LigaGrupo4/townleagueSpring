package com.sanvalero.townleague.appwebcontroller;

import com.sanvalero.townleague.domain.Referee;
import com.sanvalero.townleague.domain.Stadium;
import com.sanvalero.townleague.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

public class StadiumAppWebController {

    @Autowired
    StadiumService stadiumService;

    @RequestMapping(value = "/web-stadiums", method = RequestMethod.GET)
    public String referee(Model model) {
        Set<Stadium> stadiums = stadiumService.findAllStadiums();
        model.addAttribute("stadiums", stadiums);
        return "stadium";
    }
}
