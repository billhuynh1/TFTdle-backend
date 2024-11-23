package com.tftdle.controller;


import com.tftdle.model.GuessModel;
import com.tftdle.model.GuessResponse;
import com.tftdle.service.ChampService;
import com.tftdle.service.GuessService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/guess")
public class GuessController {

    @Autowired
    GuessService guessService;

    @Autowired
    ChampService champService;

    GuessResponse guessResponse;

    @PostMapping("/save")
    public ResponseEntity<String> saveGuess(@RequestBody GuessModel champRequest, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String champ = champRequest.getChamp();
        guessService.saveGuess(champ, sessionId);
        return ResponseEntity.ok("Guessed Save for " + champ);
    }

    @GetMapping("/get")
    public ResponseEntity<List<GuessModel>> getGuess(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        List<GuessModel> response = guessService.getGuess(sessionId);
        return ResponseEntity.ok(response);
    }
}
