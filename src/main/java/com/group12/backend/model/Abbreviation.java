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
    private Boolean approved = false;

    @JoinColumn(name="department_id", nullable=true)
    private Long department_id;

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
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Id setter
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Letters getter
     * @return
     */
    public String getLetters() {
        return letters;
    }

    /**
     * Letters setter
     * @param letters
     */
    public void setLetters(String letters) {
        this.letters = letters;
    }

    /**
     * Meaning getter
     * @return
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * Meaning setter
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


    public Long getDepartment() {
        return department_id;
    }

    /**
     * Department setter
     * @param department
     */
    public void setDepartment_id(Long department) {
        this.department_id = department;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    /**
     * Approved getter
     * @return
     */
    public Boolean getApproved() {
        return approved;
    }


    /**
     * Likes getter
     * @return
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Likes setter
     * @param likes
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * If the likes of abbreviation is higher than 3
     * Abbreviation is accepted
     */
    public void giveLike() {
        likes++;
        if (likes > 3) {
            acceptAbbreviation();
        }
    }


    /**
     * Gives an abbreviation -1 like.
     */
    public void giveDislike() {
        likes--;
    }


    /**
     * approves an abbreviation
     */
    private void acceptAbbreviation() {
        approved = true;
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
        Abbreviation abbreviation = (Abbreviation) o;
        return id == abbreviation.id && Objects.equals(letters, abbreviation.letters) && Objects.equals(meaning, abbreviation.meaning);
    }

    /**
     * Hashcode function
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, letters, meaning);
    }

    /**
     * ToString function
     * @return
     */
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
