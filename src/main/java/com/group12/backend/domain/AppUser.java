package com.group12.backend.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * This class is the model for the user, it is named AppUser to distinct itself from standard User
 * classes used in Spring Boot. The Entity annotation makes sure the model is linked to the database
 * and all the columns are added as well.
 */
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    //email kan in java gerepresenteerd worden als string
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    /**
     * Constructor(empty)
     */
    public AppUser() {
    }

    /**
     * Constructor with parameters
     * @param id
     * @param name
     * @param username
     * @param password
     * @param roles
     */
    public AppUser(Long id, String name, String username, String password, Collection<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
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

    /**
     * Name setter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Username getter
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username setter
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Password getter
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password setter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Role getter for user
     * @return
     */
    public Collection<Role> getRoles() {
        return roles;
    }

    /**
     * Role setter for user
     * @return
     */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    /**
     * equals function
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) && Objects.equals(name, appUser.name) && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(roles, appUser.roles);
    }

    /**
     * Hashcode function
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, roles);
    }

    /**
     * ToString function
     * @return
     */
    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
