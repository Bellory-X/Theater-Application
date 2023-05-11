package com.example.theater.dao.entity.performance;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "performance")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Performance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "id_play")
    int idPlay;
    @Column(name = "id_repertoire")
    int idRepertoire;
    @Column(name = "price")
    int price;
    @Column(name = "theater")
    String theater;
}
