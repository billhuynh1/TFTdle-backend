package com.tftdle.service;

import com.tftdle.model.SessionModel;
import com.tftdle.repository.SessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SessionService {
    @Autowired
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    // Converts session creation time into date format (mm/dd/yyyy)
    // to check if it is equal to current date, if not, then the session
    // should be invalid.
    public boolean isSessionValid(HttpSession session) {
        LocalDate sessionCreationTimeToLocalDate = Instant.ofEpochMilli(session.getCreationTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate today = LocalDate.now();
        if (!sessionCreationTimeToLocalDate.equals(today)) {
            session.invalidate();
            return false;
        }
        return true;
    }

    public SessionModel getSessionFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || !isSessionValid(session)) {
            session = request.getSession(true);
        }
        SessionModel sessionModel = new SessionModel();
        sessionModel.setSessionId(session.getId());
        sessionModel.setCreatedAt(new Date());
        return sessionModel;
    }

    public void saveSessionToDatabase(SessionModel sessionModel) {
        sessionRepository.save(sessionModel);
    }
}
