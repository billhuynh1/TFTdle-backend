package com.tftdle.service;

import com.tftdle.model.ChampModel;
import com.tftdle.repository.ChampRepository;
import com.tftdle.repository.GuessRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ChampService {
    private static final Logger log = LoggerFactory.getLogger(ChampService.class);
    @Autowired
    private ChampRepository champRepository;
    @Autowired
    private GuessRepository guessRepository;
    private ChampModel dailyChamp;

    @Transactional(readOnly = true)
    public List<ChampModel> getChamps() {
        return champRepository.findAll();
    }

    public ChampModel getDailyChamp() {
        if (dailyChamp == null) {
            log.warn("Daily champ not initialized");
            updateDailyChamp();
        }
        return dailyChamp;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateDailyChamp() {
        try {
            List<ChampModel> champs = getChamps();
            if (champs.isEmpty()) {
                log.warn("Champion list is empty");
                return;
            }
            LocalDate currentDate = LocalDate.now();
            int currentDay = currentDate.getDayOfYear();
            this.dailyChamp = champs.get(currentDay % champs.size());
            System.out.println("Today's champ: " + dailyChamp.getName());
        } catch (Exception e) {
            log.error("Error from server getting daily champ : {}", e.getMessage());
            throw e;
        }
    }
}
