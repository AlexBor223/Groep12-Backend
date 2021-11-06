package com.group12.backend.service;

import com.group12.backend.model.Abbreviation;

import java.util.List;

public interface AbbreviationService {
    Abbreviation saveAbbreviation(Abbreviation abbreviation);

    List<Abbreviation> getAllAbbreviations();

    List<Abbreviation> getFilteredAbbreviations(String letters, String meaning, String department);

    Abbreviation getAbbreviationById(long id);

    Abbreviation updateAbbreviation(Abbreviation abbreviation, long id);

    void deleteAbbreviation(long id);

    void likeAbbreviation(long id);

    void dislikeAbbreviation(long id);
}
