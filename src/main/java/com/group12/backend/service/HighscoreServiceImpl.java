package com.group12.backend.service;

import com.group12.backend.model.Highscore;
import com.group12.backend.repository.HighscoreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HighscoreServiceImpl implements HighscoreService {
    private HighscoreRepository highscoreRepository;

    public HighscoreServiceImpl(HighscoreRepository highscoreRepository) {
        this.highscoreRepository = highscoreRepository;
    }

    public Highscore addHighScore(Highscore highscore){
        return highscoreRepository.save(highscore);
    }

    public List<Highscore> getHighScore(){

        List<Highscore> highscores= (List<Highscore>) highscoreRepository.getTopHighscores();
        System.out.println(highscores);
        return highscores;
    }
}
