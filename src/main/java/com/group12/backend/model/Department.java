package com.group12.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "letters", nullable = true)
    private String letters;

    public Department() {
    }

    /**
     * Constructor with parameters
     *
     * @param name
     * @param letters
     */
    public Department(String name, String letters) {
        this.name = name;
        this.letters = letters;
    }

    /**
     * Constructor with parameters
     *
     * @param id
     * @param name
     * @param letters
     */
    public Department(long id, String name, String letters) {
        this.id = id;
        this.name = name;
        this.letters = letters;
    }

    public long getid() {
        return id;
    }

    /**
     * Id setter
     *
     * @param department
     */
    public void setid(long department) {
        this.id = department;
    }

    /**
     * Name getter
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Name setter
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Letters getter
     *
     * @return
     */
    public String getLetters() {
        return letters;
    }

    /**
     * Letters setter
     *
     * @param letters
     */
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

    /**
     * ToString function
     *
     * @return
     */
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", letters='" + letters + '\''
                ;
    }
}
