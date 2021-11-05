package com.group12.backend.service;

import com.group12.backend.model.Abbreviation;
import com.group12.backend.model.Department;

import java.util.List;

public interface AbbreviationService {
    Abbreviation saveAbbreviation(Abbreviation abbreviation);

    List<Abbreviation> getAllAbbreviations();

    Abbreviation getAbbreviationById(long id);

    Abbreviation updateAbbreviation(Abbreviation abbreviation, long id);

    void deleteAbbreviation(long id);

    void likeAbbreviation(long id);

    void dislikeAbbreviation(long id);
}
