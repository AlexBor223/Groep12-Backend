package com.group12.backend.controller;


import com.group12.backend.model.Department;
import com.group12.backend.model.Highscore;
import com.group12.backend.service.HighscoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/highscores")
public class HighscoreController {
    private final HighscoreService highscoreService;

    public HighscoreController(HighscoreService highscoreService) {
        this.highscoreService = highscoreService;
    }

    @GetMapping
    public List<Highscore> getAllHighscores(){
        System.out.println(highscoreService.getHighScore());
        return highscoreService.getHighScore();
    }

    @PostMapping
    public ResponseEntity<Highscore> setHighscore(@RequestBody Highscore highscore){
        return new ResponseEntity<>(highscoreService.addHighScore(highscore), HttpStatus.CREATED);
    }





}
