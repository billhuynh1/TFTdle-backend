package com.tftdle.service;

import com.tftdle.model.GuessModel;
import com.tftdle.repository.GuessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GuessService {
    private static final Logger log = LoggerFactory.getLogger(GuessService.class);
    private final GuessRepository guessRepository;
    private final ChampService champService;

    @Autowired
    public GuessService(GuessRepository guessRepository, ChampService champService) {
        this.guessRepository = guessRepository;
        this.champService = champService;
    }

    public String getDailyChamp() {
        return champService.getDailyChamp().getName();
    }


    public void saveGuess(String champ, String sessionId) {
        try {
            GuessModel guess = new GuessModel();
            String dailyChamp = getDailyChamp();
            boolean isCorrect = isGuessedCorrectly(champ, dailyChamp);
            guess.setIsCorrect(isCorrect);
            guess.setChamp(champ);
            guess.setSessionId(sessionId);
            guessRepository.save(guess);
        } catch (Exception e) {
            log.error(String.valueOf("Error saving a champ : " + e));
        }
    }

    @Transactional
    public List<GuessModel> getGuess(String sessionId) {
        return guessRepository.findGuessesBySessionId(sessionId);
    }

    public boolean isGuessedCorrectly(String champ, String dailyChamp) {
        return champ.trim().equalsIgnoreCase(dailyChamp.trim());
    }
}
