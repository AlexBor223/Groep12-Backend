package com.group12.backend.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Abbreviations")
public class Abbreviation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "letters", nullable = false)
    private String letters;

    @Column(name = "meaning", nullable = false)
    private String meaning;

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "approved", nullable = false)
    private boolean approved = false;

    @JoinColumn(name = "department_id", nullable = true)
    private long department_id;

    public Abbreviation() {

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

    /**
     * Id getter
     *
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Id setter
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
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

    /**
     * Meaning getter
     *
     * @return
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * Meaning setter
     *
     * @param meaning
     */
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
        return department_id;
    }

    public void setDepartment_id(long department) {
        this.department_id = department;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean getApproved() {
        return approved;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void giveLike() {
        likes++;
        if (likes > 3) {
            acceptAbbreviation();
        }
    }

    public void giveDislike() {
        likes--;
    }

    private void acceptAbbreviation() {
        approved = true;
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
                ", department='" + department_id + '\'' +
                ", approved='" + approved + '\'' +
                ", likes='" + likes + '\'' +
                ", test='" + "likes" + '\'' +
                '}';
    }

}
