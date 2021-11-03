package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbbreviationServiceImpl implements AbbreviationService{

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
    public Abbreviation getAbbreviationById(long id){
        Optional<Abbreviation> abbreviation = abbreviationRepository.findById(id);
        if (abbreviation.isPresent()){
            return abbreviation.get();
        }
        else {
            throw new ResourceNotFoundException("Abbreviation", "id", id);
        }
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
    public void likeAbbreviation(long id){
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));
        existingAbbreviation.giveLike();
        abbreviationRepository.save(existingAbbreviation);
    }

    @Override
    public void dislikeAbbreviation(long id){
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "id", id));
        existingAbbreviation.giveDislike();
        abbreviationRepository.save(existingAbbreviation);
    }

}
