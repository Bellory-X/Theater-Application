package com.example.theater.dao.entity.employee.character;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "characters_musician")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CharactersMusician {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "id_employee")
    int idEmployee;
    @Column(name = "character")
    String character;
}
