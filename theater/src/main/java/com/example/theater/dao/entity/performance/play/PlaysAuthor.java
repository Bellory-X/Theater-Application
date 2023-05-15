package com.example.theater.dao.entity.performance.play;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "plays_author")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlaysAuthor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "id_author")
    int idAuthor;
    @Column(name = "id_play")
    int idPlay;

//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name = "id_author")
//    Author author;
}
