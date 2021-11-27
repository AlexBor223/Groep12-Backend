package com.group12.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "Highscore")
public class Highscore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long Highscore;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "score", nullable = false)
    public int score;

//    @Override
//    public String toString() {
//        return "Department{" +
//                "id=" + Highscore +
//                ", name='" + name + '\'' +
//                ", score='" + score + '\''+"}"
//                ;
//    }
}
