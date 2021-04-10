package com.sanvalero.townleague.appwebcontroller;

import com.sanvalero.townleague.domain.Referee;
import com.sanvalero.townleague.service.RefereeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

@Controller
class RefereeAppWebController {

    private final Logger logger = LoggerFactory.getLogger(RefereeAppWebController.class);

    @Autowired
    private RefereeService refereeService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("inicio", "INDEX");
        return "index";
    }

    @RequestMapping(value = "/web-referees", method = RequestMethod.GET)
    public String referee(Model model) {
        logger.info("init showReferees");
        Set<Referee> referees = refereeService.findAllReferees();
        model.addAttribute("referee", referees);
        logger.info("end showReferees");
        return "referee";
    }

}
