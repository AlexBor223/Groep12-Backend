package com.group12.backend.service;

import com.group12.backend.model.Abbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import org.springframework.stereotype.Service;

@Service
public class AbbreviationServiceImpl implements AbbreviationService{

    private AbbreviationRepository abbreviationRepository;

    public AbbreviationServiceImpl(AbbreviationRepository abbreviationRepository) {
        super();
        this.abbreviationRepository = abbreviationRepository;
    }

    @Override
    public Abbreviation saveAbbreviation(Abbreviation abbreviation) {
        return abbreviationRepository.save(abbreviation);
    }
}
