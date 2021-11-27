package com.group12.backend.service;

import com.group12.backend.model.Highscore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HighscoreService {

    public Highscore addHighScore(Highscore highscore);
    public List<Highscore> getHighScore();
}
