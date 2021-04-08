package com.sanvalero.townleague.appwebcontroller;

import com.sanvalero.townleague.domain.Match;
import com.sanvalero.townleague.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;


@Controller
public class MatchAppWebController {

    @Autowired
    MatchService matchService;

    @RequestMapping(value = "/web-matches")
    public String matches(Model model){
        Set<Match> matches = matchService.findAll();
        model.addAttribute("matches", matches);
        return "matches";
    }
}
