package com.group12.backend.repository;

import com.group12.backend.model.Abbreviation;
import com.group12.backend.model.Highscore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Abbreviation repository is the DAO
 */
public interface AbbreviationRepository extends JpaRepository<Abbreviation, Long> {
    @Query("SELECT a FROM Abbreviation a WHERE a.department_id = ?1 AND a.letters like ?2 ")
    List<Abbreviation> getFilteredAbbreviations(long department, String abbreviation);
    @Query("SELECT a FROM Abbreviation a WHERE a.letters like ?1 ")
    List<Abbreviation> getFilteredAbbreviations( String abbreviation);
    @Query("SELECT a FROM Abbreviation a WHERE a.department_id = ?1 ")
    List<Abbreviation> getFilteredAbbreviations(long department);
}
