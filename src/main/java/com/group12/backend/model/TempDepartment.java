package com.group12.backend.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class TempDepartment {

    public long id;


    public String name;

    public String letters;


    public TempDepartment(Department abbreviation){
        id =abbreviation.getId();
        letters = abbreviation.getLetters();
        name = abbreviation.getName();
    }
}
