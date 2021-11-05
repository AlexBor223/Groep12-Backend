package com.group12.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


    public class TempAbbreviation {


        private long id;

        private String letters;

        private String meaning;

        private Boolean approved = false;

        @JsonProperty("departmentId")
        private long departmentId;


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

//    public String getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(String department) {
//        this.department = department;
//    }


        public long getDepartment() {
            return departmentId;
        }

        public Boolean getApproved() {
            return approved;
        }



    }

