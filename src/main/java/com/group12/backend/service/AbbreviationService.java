package com.group12.backend.service;

import com.group12.backend.model.Abbreviation;

import java.util.List;

/**
 * Abbreviation service interface
 */
public interface AbbreviationService {
    Abbreviation saveAbbreviation(Abbreviation abbreviation);

    /**
     * super function for getting all abbreviations
     * @return
     */
    List<Abbreviation> getAllAbbreviations();

    /**
     * super function for getting filtered abbreviations
     * @param letters
     * @param meaning
     * @param department
     * @return
     */
    List<Abbreviation> getFilteredAbbreviations(String letters, String meaning, String department);

    /**
     * super function for getting single abbreviation by id
     * @param id
     * @return
     */
    Abbreviation getAbbreviationById(long id);

    /**
     * super function for updating an abbreviation
     * @param abbreviation
     * @param id
     * @return
     */
    Abbreviation updateAbbreviation(Abbreviation abbreviation, long id);

    /**
     * super function for deleting an abbreviation
     * @param id
     */
    void deleteAbbreviation(long id);

    /**
     * super function for liking an abbreviation
     * @param id
     */
    void likeAbbreviation(long id);

    /**
     * super function for disliking an abbreviation
     * @param id
     */
    void dislikeAbbreviation(long id);
}
