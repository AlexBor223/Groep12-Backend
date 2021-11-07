package com.group12.backend.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Department model contains data about departments of the Rijksoverheid.
 *
 * The data can be modified by making use of the setters, the data can be retrieved using the getters.
 * The table annotation specifies which table correlates with this object.
 * @author Plinio
 */

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "letters", nullable = false)
    private String letters;

    public Department() {
    }

    public Department(String name, String letters) {
        this.name = name;
        this.letters = letters;
    }

    public Department(long id, String name, String letters) {
        this.id = id;
        this.name = name;
        this.letters = letters;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, letters);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", letters='" + letters + '\'' +
                '}';
    }
}
