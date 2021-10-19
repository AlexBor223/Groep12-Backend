package com.group12.backend.controller;

import com.group12.backend.model.Abbreviation;
import com.group12.backend.service.AbbreviationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/abbreviations")
public class AbbreviationController {
    private AbbreviationService abbreviationService;

    public AbbreviationController(AbbreviationService abbreviationService){
        super();
        this.abbreviationService = abbreviationService;
    }

    //REST API
    @PostMapping()
    public ResponseEntity<Abbreviation> saveAbbreviation(@RequestBody Abbreviation abbreviation){
        return new ResponseEntity<Abbreviation>(abbreviationService.saveAbbreviation(abbreviation), HttpStatus.CREATED);
    }
}
