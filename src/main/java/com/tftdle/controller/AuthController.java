package com.tftdle.controller;

import com.tftdle.model.SessionModel;
import com.tftdle.service.GuessService;
import com.tftdle.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final GuessService guessService;

    public AuthController(JwtService jwtService, GuessService guessService) {
        this.jwtService = jwtService;
        this.guessService = guessService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody SessionModel session) {
        String sessionId = session.getSessionId();

        String accessToken = jwtService.generateAccessToken(sessionId);
        String refreshToken = jwtService.generateRefreshToken(sessionId);

        // Store refresh token in database
        guessService.saveGuess("placeholder", refreshToken);

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (!jwtService.validateToken(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token");
        }

        String sessionId = jwtService.extractUserId(refreshToken);
        String newAccessToken = jwtService.generateAccessToken(sessionId);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

}
