package com.group12.backend.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class is the model for the Role. The Entity annotation makes sure the model is linked to the database
 * and all the columns are added as well.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //name of the role: Admin
    private String name;

    /**
     * Constructor without parameters.
     */
    public Role() {
    }

    /**
     * Role constructor with parameters.
     * @param id
     * @param name
     */
    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Id getter
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Id setter
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Name getter
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
