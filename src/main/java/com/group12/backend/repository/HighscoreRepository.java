package com.group12.backend.repository;


import com.group12.backend.model.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HighscoreRepository extends JpaRepository<Highscore, Long> {
    @Query("SELECT s FROM Highscore s")
    List<Highscore> getTopHighscores();
}
