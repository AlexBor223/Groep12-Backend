package com.group12.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="abbreviation")
public class Abbreviation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "letters", nullable = false)
    private String letters;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    public Abbreviation(){

    }

    public Abbreviation(long id, String letters, String meaning) {
        this.id = id;
        this.letters = letters;
        this.meaning = meaning;
    }

    public Abbreviation(String letters, String meaning) {
        this.letters = letters;
        this.meaning = meaning;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abbreviation abbreviation = (Abbreviation) o;
        return id == abbreviation.id && Objects.equals(letters, abbreviation.letters) && Objects.equals(meaning, abbreviation.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, letters, meaning);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", letters='" + letters + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }
}
