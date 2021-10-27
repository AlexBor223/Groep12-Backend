package com.group12.backend.controller;

import com.group12.backend.model.Abbreviation;
import com.group12.backend.service.AbbreviationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/abbreviations")
public class AbbreviationController {

    private AbbreviationService abbreviationService;

    public AbbreviationController(AbbreviationService abbreviationService){
        super();
        this.abbreviationService = abbreviationService;
    }

    //REST API create abbreviation.
    @PostMapping()
    public ResponseEntity<Abbreviation> saveAbbreviation(@RequestBody Abbreviation abbreviation){
        return new ResponseEntity<Abbreviation>(abbreviationService.saveAbbreviation(abbreviation), HttpStatus.CREATED);
    }

    //REST API get all abbreviations.
    @GetMapping
    public List<Abbreviation> getAllAbbreviations(){
        return abbreviationService.getAllAbbreviations();
    }

    //REST API get one specific abbreviation. /api/abbreviations/1
    @GetMapping("{id}")
    public ResponseEntity<Abbreviation> getAbbrevationById(@PathVariable("id") long abbreviationId){
        return new ResponseEntity<Abbreviation>(abbreviationService.getAbbreviationById(abbreviationId), HttpStatus.OK);
    }
}
