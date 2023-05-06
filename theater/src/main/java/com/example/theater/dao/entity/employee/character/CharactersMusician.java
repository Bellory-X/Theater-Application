package com.example.theater.dao.entity.employee.character;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CharactersMusician")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CharactersMusician {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "IdEmployee")
    int idEmployee;
    @Column(name = "character")
    String character;
}
