package com.sanvalero.townleague.appwebcontroller;

import com.sanvalero.townleague.domain.Referee;
import com.sanvalero.townleague.domain.Stadium;
import com.sanvalero.townleague.service.StadiumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class StadiumAppWebController {

    private final Logger logger = LoggerFactory.getLogger(StadiumAppWebController.class);

    @Autowired
    StadiumService stadiumService;

    @RequestMapping(value = "/web-stadiums", method = RequestMethod.GET)
    public String referee(Model model) {
        logger.info("init showStadiums");
        Set<Stadium> stadiums = stadiumService.findAllStadiums();
        model.addAttribute("stadiums", stadiums);
        logger.info("end showStadiums");
        return "stadium";
    }
}
