package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Subscription")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subscription {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "Count")
    int count;
    @Column(name = "Price")
    int price;

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
