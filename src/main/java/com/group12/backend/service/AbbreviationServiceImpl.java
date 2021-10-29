package com.group12.backend.service;

import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Abbreviation getAbbreviationById(long id){
        Optional<Abbreviation> abbreviation = abbreviationRepository.findById(id);

        if (abbreviation.isPresent())
            return abbreviation.get();

        throw new ResourceNotFoundException("Abbreviation", "id", id);
    }
}
