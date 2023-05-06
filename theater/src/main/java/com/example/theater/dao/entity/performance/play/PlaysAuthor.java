package com.example.theater.dao.entity.performance.play;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "PlaysAuthor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlaysAuthor {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "IdAuthor")
    int idAuthor;
    @Column(name = "IdPlay")
    int idPlay;
}
