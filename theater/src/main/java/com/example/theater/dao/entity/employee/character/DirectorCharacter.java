package com.example.theater.dao.entity.employee.character;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "DirectorCharacter")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DirectorCharacter {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "character")
    String character;
}
