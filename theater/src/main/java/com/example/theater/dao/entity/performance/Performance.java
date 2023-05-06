package com.example.theater.dao.entity.performance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "performance")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Performance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "id_play")
    int idPlay;
    @Column(name = "id_repertoire")
    int idRepertoire;
    @Column(name = "price")
    int price;
    @Column(name = "theater")
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
