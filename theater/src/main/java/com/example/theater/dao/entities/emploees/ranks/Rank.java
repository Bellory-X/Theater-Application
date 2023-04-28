package com.example.theater.dao.entities.emploees.ranks;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Rank")
@Getter
@Setter
public class Rank {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "Name")
    String name;
    @Column(name = "Contest")
    String contest;
    @Column(name = "Data")
    Date data;

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
