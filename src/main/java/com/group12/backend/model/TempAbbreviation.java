package com.group12.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;


import javax.persistence.*;
import java.util.Objects;
import java.util.stream.Collectors;


public class TempAbbreviation {



        public long id;

        public String letters;

        public String meaning;


        public Boolean approved = false;

        @JsonProperty("departmentId")
        public long departmentId;


        public TempAbbreviation(Abbreviation abbreviation){
            id =abbreviation.getId();
            letters = abbreviation.getLetters();
            meaning = abbreviation.getMeaning();
            approved = abbreviation.getApproved();
            departmentId = abbreviation.getDepartment().getId();
        }
        public TempAbbreviation(){}

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getLetters() {
            return letters;
        }

        public void setLetters(String letters) {
            this.letters = letters;
        }

        public String getMeaning() {
            return meaning;
        }

        public void setMeaning(String meaning) {
            this.meaning = meaning;
        }

        public long getDepartment() {
            return departmentId;
        }




    }

