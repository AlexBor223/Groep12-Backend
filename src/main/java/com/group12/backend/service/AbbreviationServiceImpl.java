package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import com.group12.backend.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbbreviationServiceImpl implements AbbreviationService {

    private final AbbreviationRepository abbreviationRepository;
    private final DepartmentRepository departmentRepository;

    public AbbreviationServiceImpl(AbbreviationRepository abbreviationRepository, DepartmentRepository departmentRepository) {
        this.abbreviationRepository = abbreviationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveAbbreviation(Abbreviation abbreviation) {
        abbreviationRepository.save(abbreviation);
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
            throw new ResourceNotFoundException("Abbreviation", "Id", id);
        }
    }

    @Override
    public List<Abbreviation> getFilteredAbbreviations(String letters, String meaning, String department) {

        if (department == null && letters == null){
            return abbreviationRepository.findAll();
        }
        else if (letters == null){
            return abbreviationRepository.getFilteredAbbreviations(departmentRepository.findByName(department).getid());
        }
        else if (department == null){
            return abbreviationRepository.getFilteredAbbreviations(letters);
        }
        else {
            return abbreviationRepository.getFilteredAbbreviations(departmentRepository.findByName(department).getid(), letters);
        }
    }

    @Override
    public Abbreviation updateAbbreviation(Abbreviation abbreviation, long id) {
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Abbreviation", "Id", id));
        existingAbbreviation.setLetters(abbreviation.getLetters());
        existingAbbreviation.setMeaning(abbreviation.getMeaning());
        existingAbbreviation.setDepartmentId(abbreviation.getDepartmentId());
        existingAbbreviation.setApproved(abbreviation.getApproved());

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
                () -> new ResourceNotFoundException("Abbreviation", "Id", id));
        existingAbbreviation.giveLike();
        abbreviationRepository.save(existingAbbreviation);
    }

    @Override
    public void dislikeAbbreviation(long id) {
        //Check whether abbreviation exists in db
        Abbreviation existingAbbreviation = abbreviationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Abbreviation", "Id", id));
        existingAbbreviation.giveDislike();
        if (existingAbbreviation.getLikes() < -9) {
            deleteAbbreviation(id);

            return;
        }
        abbreviationRepository.save(existingAbbreviation);
    }
}
