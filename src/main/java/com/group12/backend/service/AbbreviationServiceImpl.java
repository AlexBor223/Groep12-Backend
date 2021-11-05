package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class AbbreviationServiceImpl implements AbbreviationService {
    private AbbreviationRepository abbreviationRepository;

    public AbbreviationServiceImpl(AbbreviationRepository abbreviationRepository) {
        this.abbreviationRepository = abbreviationRepository;
    }

    @Override
    public Abbreviation saveAbbreviation(Abbreviation abbreviation) {
        return abbreviationRepository.save(abbreviation);
    }

    @Override
    public List<Abbreviation> getAllAbbreviations() {
        return abbreviationRepository.findAll();
    }

    @Override
    public Abbreviation getAbbreviationById(long id) {
        Optional<Abbreviation> abbreviation = abbreviationRepository.findById(id);
        if (abbreviation.isPresent()) {
            return abbreviation.get();
        } else {
            throw new ResourceNotFoundException("Abbreviation", "id", id);
        }
    }

    @Override
    public List<Abbreviation> getFilteredAbbreviations(String letters, String meaning, String department) {
        List<Abbreviation> abbreviations = abbreviationRepository.findAll();
        List<Abbreviation> filteredAbbreviations = new ArrayList<>();

        if (areAllNull(letters, meaning, department))
            throw new ResourceNotFoundException("Abbreviations", "filter", "All paramaters are null");

        for (Abbreviation abbreviation : abbreviations) {
            boolean lettersIsIdentical = abbreviation.getLetters().equals(letters);
            boolean meaningIsIdentical = abbreviation.getMeaning().equals(meaning);
            boolean departmentIsIdentical = abbreviation.getDepartment().equals(department);

            if (areAllNotNull(letters, meaning, department)) {
                if (lettersIsIdentical && meaningIsIdentical && departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (areAllNotNull(meaning, department) && areAllNull(letters)) {
                if (meaningIsIdentical && departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (areAllNotNull(letters, department) && areAllNull(meaning)) {
                if (lettersIsIdentical && departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            } else if (areAllNotNull(department) && areAllNull(letters, meaning)) {
                if (departmentIsIdentical)
                    filteredAbbreviations.add(abbreviation);
            }
        }

        return filteredAbbreviations;
    }

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

    @Override
    public void deleteAbbreviation(long id) {
        //Check whether abbreviation exists in db
        abbreviationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Abbreviation", "Id", id));
        abbreviationRepository.deleteById(id);
    }

    @Override
    public void likeAbbreviation(long id) {
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));
        existingAbbreviation.giveLike();
        abbreviationRepository.save(existingAbbreviation);
    }

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

    public boolean areAllNotNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::nonNull);
    }
}
