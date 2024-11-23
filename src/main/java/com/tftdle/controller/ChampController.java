package com.tftdle.controller;

import com.tftdle.model.ChampModel;
import com.tftdle.repository.ChampRepository;
import com.tftdle.repository.SessionRepository;
import com.tftdle.service.ChampService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ChampController {

    @Autowired
    private ChampService champService;

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/champs")
    public List<ChampModel>getChamps() {
        return champService.getChamps();
    }

    @GetMapping("/dailychamp")
    public ChampModel getDailyChamp() {
        return champService.getDailyChamp();
    }

    @PostMapping("/save")
    public String saveChamps(@RequestBody List<String> guessedChamps, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getAttribute("guesses");
        session.setAttribute("guesses", guessedChamps);
        return "Session exists with ID: " + session.getId();
    }


}


