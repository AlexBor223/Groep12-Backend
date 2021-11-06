package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.model.Department;
import com.group12.backend.model.TempAbbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import com.group12.backend.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class AbbreviationServiceImpl implements AbbreviationService {

    private AbbreviationRepository abbreviationRepository;
    private DepartmentRepository departmentRepository;

    public AbbreviationServiceImpl(AbbreviationRepository abbreviationRepository, DepartmentRepository departmentRepository) {
        this.abbreviationRepository = abbreviationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void saveAbbreviation(TempAbbreviation tempAbbreviation) {
        tempAbbreviation.setDepartmentId(1);
        Abbreviation abbreviation = new Abbreviation(tempAbbreviation, departmentRepository.getById(tempAbbreviation.getDepartment()));
        Department department = departmentRepository.getById(tempAbbreviation.getDepartment());
        department.addAbbreviation(abbreviation);
        departmentRepository.save(department);
        abbreviationRepository.save(abbreviation);
    }

    @Override
    public List<TempAbbreviation> getAllAbbreviations() {


        return abrListToTAbrList(abbreviationRepository.findAll());
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
    public List<TempAbbreviation> getFilteredAbbreviations(String letters, String meaning, String department) {
        List<Abbreviation> abbreviations = abbreviationRepository.findAll();
        List<Abbreviation> filteredAbbreviations = new ArrayList<>();

        System.out.println(letters+meaning+department);

        if (areAllNull(letters, meaning, department))
            throw new ResourceNotFoundException("Abbreviations", "filter", "All parameters are null");
        return  abrListToTAbrList(abbreviations);

//        for (Abbreviation abbreviation : abbreviations) {
//            boolean lettersIsIdentical = abbreviation.getLetters().contains(letters);
//            boolean meaningIsIdentical = abbreviation.getMeaning().equals(meaning);
//            boolean departmentIsIdentical = abbreviation.getDepartment().equals(department);
//            System.out.println("test1");
//            if (areAllNotNull(letters, meaning, department)) {
//                if (lettersIsIdentical && meaningIsIdentical && departmentIsIdentical)
//                    filteredAbbreviations.add(abbreviation);
//            } else if (areAllNotNull(meaning, department) && areAllNull(letters)) {
//                if (meaningIsIdentical && departmentIsIdentical)
//                    filteredAbbreviations.add(abbreviation);
//            } else if (areAllNotNull(letters, department) && areAllNull(meaning)) {
//                if (lettersIsIdentical && departmentIsIdentical)
//                    filteredAbbreviations.add(abbreviation);
//            } else if (areAllNotNull(department) && areAllNull(letters, meaning)) {
//                if (departmentIsIdentical)
//                    filteredAbbreviations.add(abbreviation);
//            } else if (areAllNotNull(letters) && areAllNull(meaning, department)) {
//                if (lettersIsIdentical)
//                    filteredAbbreviations.add(abbreviation);
//            } else if (areAllNotNull(meaning) && areAllNull(letters, department)) {
//                if (meaningIsIdentical)
//                    filteredAbbreviations.add(abbreviation);
//            }
//        }
//
//        return abrListToTAbrList(filteredAbbreviations);
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

    private  List<TempAbbreviation> abrListToTAbrList(List<Abbreviation> abbreviations){
        List<TempAbbreviation> newList = new ArrayList<TempAbbreviation>(abbreviations.size());

        for (Abbreviation abr : abbreviations) {
            newList.add(new TempAbbreviation(abr));
        }
        return newList;
    }

    public boolean areAllNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::isNull);
    }

    public boolean areAllNotNull(Object... objects) {
        return Stream.of(objects).allMatch(Objects::nonNull);
    }
}
