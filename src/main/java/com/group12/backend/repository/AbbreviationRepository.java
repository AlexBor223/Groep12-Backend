package com.group12.backend.repository;

import com.group12.backend.model.Abbreviation;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Abbreviation repository is the DAO
 */
public interface AbbreviationRepository extends JpaRepository<Abbreviation, Long> {

}
