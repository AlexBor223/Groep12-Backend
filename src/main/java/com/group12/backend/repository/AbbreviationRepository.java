package com.group12.backend.repository;

import com.group12.backend.model.Abbreviation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AbbreviationRepository extends JpaRepository<Abbreviation, Long> {

}
