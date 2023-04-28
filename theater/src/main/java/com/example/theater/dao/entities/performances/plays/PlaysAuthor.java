package com.example.theater.dao.entities.performances.plays;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PlaysAuthor")
@Getter
@Setter
@IdClass(PlaysAuthorKey.class)
public class PlaysAuthor {
    @Id
    @Column(name = "IdAuthor")
    int idAuthor;
    @Id
    @Column(name = "IdPlay")
    int idPlay;
}
