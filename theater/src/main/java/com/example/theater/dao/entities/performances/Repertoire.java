package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Repertoire")
@Getter
@Setter
public class Repertoire {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "Number")
    int count;
    @Column(name = "Theater")
    String price;

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
