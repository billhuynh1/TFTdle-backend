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
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "https://tftdle.vercel.app"}, allowCredentials = "true")
@RestController
@RequestMapping("/champs")
public class ChampController {

    @Autowired
    private ChampService champService;

    @Autowired
    private ChampRepository champRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping("/v1/get")
    public List<ChampModel>getChamps() {
        return champService.getChamps();
    }

    @GetMapping("/v1/daily")
    public ChampModel getDailyChamp() {
        return champService.getDailyChamp();
    }

    @PostMapping("/v1/save")
    public String saveChamps(@RequestBody List<String> guessedChamps, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getAttribute("guesses");
        session.setAttribute("guesses", guessedChamps);
        return "Session exists with ID: " + session.getId();
    }


}



