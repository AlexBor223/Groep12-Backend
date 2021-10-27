package com.group12.backend.DAO;

import com.group12.backend.model.Abbreviation;
import com.group12.backend.repository.AbbreviationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AbbreviationDao implements Dao<Abbreviation> {
    private AbbreviationRepository abbreviationRepository;

    public AbbreviationDao(AbbreviationRepository abbreviationRepository) {
        super();
        this.abbreviationRepository = abbreviationRepository;
    }

    @Override
    public Optional<Abbreviation> get(long id) {
        Optional<Abbreviation> abbreviation = abbreviationRepository.findById(id);
        return abbreviation;
    }

    @Override
    public List<Abbreviation> getAll() {
        return abbreviationRepository.findAll();
    }

    @Override
    public int save(Abbreviation abbreviation) {
        return 0;
    }

    @Override
    public void update(Abbreviation abbreviation) {

    }

    @Override
    public void delete(Abbreviation abbreviation) {

    }
}
