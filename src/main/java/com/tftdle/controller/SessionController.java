package com.tftdle.controller;

import com.tftdle.model.SessionModel;
import com.tftdle.repository.SessionRepository;
import com.tftdle.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
public class SessionController {

    private static final Logger log = LoggerFactory.getLogger(SessionController.class);
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    @PostMapping("/save")
    public ResponseEntity<String> saveSession(HttpServletRequest request) {
        SessionModel sessionModel = sessionService.getSessionFromRequest(request);
        sessionService.saveSessionToDatabase(sessionModel);
        return ResponseEntity.ok(sessionModel + " Session saved");
    }

    @GetMapping("/get")
    public ResponseEntity<SessionModel> getSessionFromRequest(HttpServletRequest request) {
            SessionModel response = sessionService.getSessionFromRequest(request);
            return ResponseEntity.ok(response);
    }
}
