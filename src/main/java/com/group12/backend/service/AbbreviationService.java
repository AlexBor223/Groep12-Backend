package com.group12.backend.service;

import com.group12.backend.model.Abbreviation;

import java.util.List;

public interface AbbreviationService {
    Abbreviation saveAbbreviation(Abbreviation abbreviation);
    List<Abbreviation> getAllAbbreviations();
    Abbreviation getAbbreviationById(long id);
}
