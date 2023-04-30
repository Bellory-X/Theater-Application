package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Repertoire")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Repertoire {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
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
