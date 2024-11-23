package com.tftdle.service;

import com.tftdle.model.ChampModel;
import com.tftdle.repository.ChampRepository;
import com.tftdle.repository.GuessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ChampService {
    @Autowired
    private ChampRepository champRepository;
    private ChampModel dailyChamp;
    private GuessRepository guessRepository;

    public List<ChampModel> getChamps() {
        return champRepository.findAll();
    }

    public Optional<ChampModel> getChampById(Long id) {
        return champRepository.findById(id);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public ChampModel getDailyChamp() {
        List<ChampModel> champs = getChamps();
        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfYear();
        if (!champs.isEmpty()) {
            dailyChamp = champs.get(currentDay % champs.size());
            System.out.println("Today's champ: " + dailyChamp.getName());
        }
        return dailyChamp;
    }
}
