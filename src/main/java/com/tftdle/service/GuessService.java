package com.tftdle.service;

import com.tftdle.model.GuessModel;
import com.tftdle.repository.GuessRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuessService {
    private final GuessRepository guessRepository;

    public GuessService(GuessRepository guessRepository) {
        this.guessRepository = guessRepository;
    }

    public void saveGuess(String champ, String sessionId) {
        GuessModel guess = new GuessModel();
        guess.setChamp(champ);
        guess.setSessionId(sessionId);
        System.out.println(guessRepository.save(guess).toString());
    }

    public List<GuessModel> getGuess(String sessionId) {
        return guessRepository.findGuessesBySessionId(sessionId);
    }

    // Changed boolean from attribute to a column in the table, fix this
    public void isGuessedCorrectly(List<String> guessedChamps, String dailyChamp, HttpSession session) {
        if (guessedChamps.contains(dailyChamp)) {
            session.setAttribute("isCorrect", true);
        } else {
            session.setAttribute("isCorrect", false);
        }
    }
}
