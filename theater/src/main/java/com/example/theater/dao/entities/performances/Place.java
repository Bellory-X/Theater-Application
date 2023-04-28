package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Place")
@Getter
@Setter
public class Place {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
