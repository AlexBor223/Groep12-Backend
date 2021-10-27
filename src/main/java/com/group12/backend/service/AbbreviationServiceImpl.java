package com.group12.backend.service;

import com.group12.backend.DAO.AbbreviationDao;
import com.group12.backend.exception.ResourceNotFoundException;
import com.group12.backend.model.Abbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbbreviationServiceImpl implements AbbreviationService{

    private AbbreviationRepository abbreviationRepository;
    private AbbreviationDao abbreviationDao;

    public AbbreviationServiceImpl(AbbreviationRepository abbreviationRepository, AbbreviationDao abbreviationDao) {
        super();
        this.abbreviationRepository = abbreviationRepository;
        this.abbreviationDao = abbreviationDao;
    }

    @Override
    public Abbreviation saveAbbreviation(Abbreviation abbreviation) {
        return abbreviationRepository.save(abbreviation);
    }

    @Override
    public List<Abbreviation> getAllAbbreviations() {
        return abbreviationDao.getAll();
    }

    @Override
    public Abbreviation getAbbreviationById(long id){
        Optional<Abbreviation> abbreviation = abbreviationDao.get(id);
        if (abbreviation.isPresent()){
            return abbreviation.get();
        }
        else {
            throw new ResourceNotFoundException("Abbreviation", "id", id);
        }
    }
}
