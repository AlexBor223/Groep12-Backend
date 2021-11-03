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
    //TODO add the api part in requestmapping to properties
    private AbbreviationService abbreviationService;

    public AbbreviationController(AbbreviationService abbreviationService){
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
        System.out.println(abbreviationService.getAllAbbreviations().get(0).getDepartment());
        return abbreviationService.getAllAbbreviations();
    }

    //REST API get one specific abbreviation. URI: /api/abbreviations/1
    @GetMapping("{id}")
    public ResponseEntity<Abbreviation> getAbbreviationById(@PathVariable("id") long abbreviationId){
        return new ResponseEntity<Abbreviation>(abbreviationService.getAbbreviationById(abbreviationId), HttpStatus.OK);
    }

    //REST API build an update for one abbreviation. URI: /api/abbreviations/1
    @PutMapping("{id}")
    public ResponseEntity<Abbreviation> updateAbbreviation(@PathVariable("id") long id, @RequestBody Abbreviation abbreviation){
        return new ResponseEntity<Abbreviation>(abbreviationService.updateAbbreviation(abbreviation, id), HttpStatus.OK);
    }

    //REST API delete on abbreviation URI: /api/abbreviations/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAbbreviation(@PathVariable("id") long id){
        abbreviationService.deleteAbbreviation(id);
        return new ResponseEntity<String>("Abbreviation deletion successful", HttpStatus.OK);
    }
    //REST api give like to abbreviation
    @PostMapping("{id}/GiveLike")
    public ResponseEntity<String> likeAbbreviation(@PathVariable("id") long id){
        abbreviationService.likeAbbreviation(id);
        return new ResponseEntity<String>("Abbreviation like given", HttpStatus.OK);
    }

    @PostMapping("{id}/GiveDisLike")
    public ResponseEntity<String> dislikeAbbreviation(@PathVariable("id") long id){
        abbreviationService.dislikeAbbreviation(id);
        return new ResponseEntity<String>("Abbreviation like given", HttpStatus.OK);
    }

}
