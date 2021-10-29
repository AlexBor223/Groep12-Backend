package com.group12.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="attributes")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "letters", nullable = false)
    private String letters;

    public Department() {}

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Abbreviation abbreviation = (Abbreviation) o;
//        return id == abbreviation.id && Objects.equals(letters, abbreviation.letters) &&
//                Objects.equals(meaning, abbreviation.meaning);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, letters);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", letters='" + letters + '\'' +
                '}';
    }
}
