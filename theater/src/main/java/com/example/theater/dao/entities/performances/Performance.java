package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Performance")
@Getter
@Setter
public class Performance {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "IdPlay")
    int idPlay;
    @Column(name = "IdRepertoire")
    int idRepertoire;
    @Column(name = "Price")
    int price;
    @Column(name = "Theater")
    String theater;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
