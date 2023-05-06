package com.example.theater.dao.entity.performance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "place")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Place {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "number")
    int number;
    @Column(name = "id_hall")
    int idHall;
    @Column(name = "price")
    int price;
    @Column(name = "reserve")
    boolean reserve;
    @Column(name = "id_subscription")
    int idSubscription;
}
