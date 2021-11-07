package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.model.NullChecker;
import com.group12.backend.repository.AbbreviationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * The implementation of AbbreviationService
 */
@Service
public class AbbreviationServiceImpl implements AbbreviationService {
    private AbbreviationRepository abbreviationRepository;
    private NullChecker nullChecker;

    /**
     * The constructor with parameters
     * @param abbreviationRepository
     */
    public AbbreviationServiceImpl(AbbreviationRepository abbreviationRepository) {
        this.abbreviationRepository = abbreviationRepository;
        nullChecker = new NullChecker();
    }

    /**
     * Saves an abbreviation from abbreviationRepository
     * @param abbreviation
     * @return
     */
    @Override
    public Abbreviation saveAbbreviation(Abbreviation abbreviation) {
        return abbreviationRepository.save(abbreviation);
    }

    /**
     * Get all the abbreviations from abbreviationRepository
     * @return
     */
    @Override
    public List<Abbreviation> getAllAbbreviations() {
        return abbreviationRepository.findAll();
    }

    /**
     * Get one abbreviation by id from abbreviationRepository
     * @param id
     * @return
     */
    @Override
    public Abbreviation getAbbreviationById(long id) {
        Optional<Abbreviation> abbreviation = abbreviationRepository.findById(id);
        if (abbreviation.isPresent()) {
            return abbreviation.get();
        } else {
            throw new ResourceNotFoundException("Abbreviation", "id", id);
        }
    }

    /**
     * Check if abbreviation is valid via nullChecker
     * @param abbreviation
     * @param letters
     * @param meaning
     * @param department
     * @return
     */
    private boolean isAbbreviationValid(Abbreviation abbreviation, String letters, String meaning, String department) {
        boolean equalsLetters = abbreviation.getLetters().equals(letters);
        boolean equalsMeaning = abbreviation.getMeaning().equals(meaning);
        boolean equalsDepartment = abbreviation.getDepartment().equals(department);

        if (nullChecker.areAllNotNull(letters, meaning, department)) {
            return equalsLetters && equalsMeaning && equalsDepartment;
        } else if (nullChecker.areAllNotNull(meaning, department) && nullChecker.areAllNull(letters)) {
            return equalsMeaning && equalsDepartment;
        } else if (nullChecker.areAllNotNull(letters, department) && nullChecker.areAllNull(meaning)) {
            return equalsLetters && equalsDepartment;
        } else if (nullChecker.areAllNotNull(department) && nullChecker.areAllNull(letters, meaning)) {
            return equalsDepartment;
        } else if (nullChecker.areAllNotNull(letters) && nullChecker.areAllNull(meaning, department)) {
            return equalsLetters;
        } else if (nullChecker.areAllNotNull(meaning) && nullChecker.areAllNull(letters, department)) {
            return equalsMeaning;
        }

        return false;
    }

    /**
     * Get all the filtered abbreviations from abbreviationRepository
     * @param letters
     * @param meaning
     * @param department
     * @return
     */
    @Override
    public List<Abbreviation> getFilteredAbbreviations(String letters, String meaning, String department) {
        List<Abbreviation> abbreviations = abbreviationRepository.findAll();
        List<Abbreviation> filteredAbbreviations = new ArrayList<>();

        if (areAllNull(letters, meaning, department))
            throw new ResourceNotFoundException("Abbreviations", "filter", "All parameters are null");

        for (Abbreviation abbreviation : abbreviations) {
            if (isAbbreviationValid(abbreviation, letters, meaning, department))
                filteredAbbreviations.add(abbreviation);
        }

        return filteredAbbreviations;
    }

    /**
     * update a abbreviation in
     * @param abbreviation
     * @param id
     * @return
     */
    @Override
    public Abbreviation updateAbbreviation(Abbreviation abbreviation, long id) {
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));
        existingAbbreviation.setLetters(abbreviation.getLetters());
        existingAbbreviation.setMeaning(abbreviation.getMeaning());
        //Save to db
        abbreviationRepository.save(existingAbbreviation);
        return existingAbbreviation;
    }

    /**
     * Delete abbreviation from abbreviationRepository
     * @param id
     */
    @Override
    public void deleteAbbreviation(long id) {
        //Check whether abbreviation exists in db
        abbreviationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Abbreviation", "Id", id));
        abbreviationRepository.deleteById(id);
    }

    /**
     * Like an abbreviation via abbreviationRepository
     * @param id
     */
    @Override
    public void likeAbbreviation(long id) {
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));
        existingAbbreviation.giveLike();
        abbreviationRepository.save(existingAbbreviation);
    }

    /**
     * Dislike an abbreviaton via abbreviationRepository
     * @param id
     */
    @Override
    public void dislikeAbbreviation(long id) {
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));
        existingAbbreviation.giveDislike();
        if (existingAbbreviation.getLikes() < -9) {
            deleteAbbreviation(id);

            return;
        }
        abbreviationRepository.save(existingAbbreviation);
    }

    /**
     * check if all are null
     * @param objects
     * @return
     */
    public boolean areAllNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }
}
