package com.sanvalero.townleague.appwebcontroller;

import com.sanvalero.townleague.domain.Match;
import com.sanvalero.townleague.domain.dto.MatchDTO;
import com.sanvalero.townleague.domain.dto.ResultDTO;
import com.sanvalero.townleague.exception.MatchNotFoundException;
import com.sanvalero.townleague.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/delete-match/{id}")
    public RedirectView deleteMatch(Model model, @PathVariable("id") long id){
        matchService.deleteMatch(id);
        return new RedirectView("/web-matches");
    }

    @GetMapping(value = "/match/{id}/insert-result")
    public String insertMatchResultForm(Model model, @PathVariable("id") long id){
        model.addAttribute("resultDTOForm", new ResultDTO());
        model.addAttribute("matchId", id);
        return "insert_result";
    }

    @PostMapping(value = "/match/{id}/insert-result")
    public RedirectView insertMatchResult(@PathVariable("id") long id, @ModelAttribute("resultDTOForm") ResultDTO resultDTO){
        matchService.insertResult(id, resultDTO);
        return new RedirectView("/web-matches");
    }
}
