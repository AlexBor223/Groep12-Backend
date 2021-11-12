package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.model.NullChecker;
import com.group12.backend.repository.AbbreviationRepository;
import com.group12.backend.repository.DepartmentRepository;
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
    private DepartmentRepository departmentRepository;
    private NullChecker nullChecker;

    public AbbreviationServiceImpl(AbbreviationRepository abbreviationRepository, DepartmentRepository departmentRepository) {
        this.abbreviationRepository = abbreviationRepository;
        this.departmentRepository = departmentRepository;
        nullChecker = new NullChecker();
    }

    /**
     * Saves an abbreviation from abbreviationRepository
     * @param abbreviation
     * @return
     */
    @Override
    public void saveAbbreviation(Abbreviation abbreviation) {
        abbreviationRepository.save(abbreviation);
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
            boolean lettersIsIdentical = abbreviation.getLetters().contains(letters);
            boolean meaningIsIdentical = abbreviation.getMeaning().equals(meaning);
            boolean departmentIsIdentical = abbreviation.getDepartment().equals(department);
            System.out.println("test1");
            if (nullChecker.areAllNotNull(letters, meaning, department)) {
                if (lettersIsIdentical && meaningIsIdentical && departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (nullChecker.areAllNotNull(meaning, department) && areAllNull(letters)) {
                if (meaningIsIdentical && departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (nullChecker.areAllNotNull(letters, department) && areAllNull(meaning)) {
                if (lettersIsIdentical && departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (nullChecker.areAllNotNull(department) && areAllNull(letters, meaning)) {
                if (departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (nullChecker.areAllNotNull(letters) && areAllNull(meaning, department)) {
                if (lettersIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (nullChecker.areAllNotNull(meaning) && areAllNull(letters, department)) {
                if (meaningIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            }
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


    public boolean areAllNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }
}
