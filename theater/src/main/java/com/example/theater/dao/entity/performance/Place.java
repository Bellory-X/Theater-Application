package com.example.theater.dao.entity.performance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Place")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Place {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "Number")
    int number;
    @Column(name = "IdHall")
    int idHall;
    @Column(name = "Price")
    int price;
    @Column(name = "Reserve")
    boolean reserve;
    @Column(name = "idSubscription")
    int idSubscription;
}