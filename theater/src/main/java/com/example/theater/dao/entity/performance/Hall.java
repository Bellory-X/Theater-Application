package com.example.theater.dao.entity.performance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hall")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hall {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "theater")
    String theater;
    @Column(name = "count_place")
    int count;
}
