package com.example.theater.dao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "subscription")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Subscription {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "count")
    int count;
    @Column(name = "price")
    int price;
}
