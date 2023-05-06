package com.example.theater.dao.entity.performance.play;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Play")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Play {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "Name")
    String name;
    @Column(name = "Data")
    Date data;
    @Column(name = "Rating")
    String rating;
    @Column(name = "Genre")
    String genre;
}