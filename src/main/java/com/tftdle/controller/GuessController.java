package com.tftdle.controller;


import brave.Response;
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

    @PostMapping("/v1/save")
    public ResponseEntity<String> saveGuess(@RequestBody GuessModel champRequest, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String sessionId = session.getId();
        String champ = champRequest.getChamp();
        guessService.saveGuess(champ, sessionId);
        return ResponseEntity.ok("Guessed Save for " + champ);
    }

    // Check if it works with frontend
    @PostMapping("/v2/{sessionId}/save")
    public ResponseEntity<String> saveGuess(@PathVariable String sessionId, @RequestBody GuessModel guess) {
        String champ = guess.getChamp();
        // Add "isCorrect" to the save
        guessService.saveGuess(champ, sessionId);
        return ResponseEntity.ok("Guessed saved for: " + sessionId + " " + guess.getChamp());
    }

    // Works with postman, check with frontend
    @GetMapping("/v2/{sessionId}/get")
    public ResponseEntity<List<GuessModel>> getGuess(@PathVariable String sessionId) {
        List<GuessModel> response = guessService.getGuess(sessionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<List<GuessModel>> getGuess(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String sessionId = session.getId();
        List<GuessModel> response = guessService.getGuess(sessionId);
        return ResponseEntity.ok(response);
    }
}
