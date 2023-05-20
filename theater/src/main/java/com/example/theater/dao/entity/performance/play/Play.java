package com.example.theater.dao.entity.performance.play;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "play")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Play {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "data")
    Date data;
    @Column(name = "rating")
    int rating;
    @Column(name = "genre")
    String genre;
}
