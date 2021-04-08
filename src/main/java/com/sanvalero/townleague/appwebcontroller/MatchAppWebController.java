package com.sanvalero.townleague.appwebcontroller;

import com.sanvalero.townleague.domain.Match;
import com.sanvalero.townleague.domain.dto.MatchDTO;
import com.sanvalero.townleague.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping(value = "/register-match")
    public String registerMatchForm(Model model){
        model.addAttribute("matchDTOForm", new MatchDTO());
        return "register_match";
    }

    @PostMapping(value = "/register-match")
    public RedirectView registerMatch(@ModelAttribute("matchDTOForm") MatchDTO matchDTO){
        matchService.addMatch(matchDTO);
        return new RedirectView("/web-matches");
    }


}
