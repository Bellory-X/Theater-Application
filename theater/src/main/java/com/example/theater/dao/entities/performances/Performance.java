package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Performance")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Performance {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "IdPlay")
    int idPlay;
    @Column(name = "IdRepertoire")
    int idRepertoire;
    @Column(name = "Price")
    int price;
    @Column(name = "Theater")
    String theater;



//    @OneToMany(fetch = FetchType.EAGER)@JoinColumn()
//    List<Hall> halls;

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
