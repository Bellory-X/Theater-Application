package com.example.theater.dao.entities.performances.plays;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Play")
@Getter
@Setter
public class Play {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
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
