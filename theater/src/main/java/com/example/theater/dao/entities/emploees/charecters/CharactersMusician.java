package com.example.theater.dao.entities.emploees.charecters;

import com.example.theater.dao.entities.emploees.Employee;
import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CharactersMusician")
@Getter
@Setter
@IdClass(CharactersMusicianKey.class)
public class CharactersMusician {
    @Id
    @Column(name = "IdEmployee")
    int id;
    @Id
    @Column(name = "character")
    String character;
    @OneToOne(mappedBy = "id")
    Employee employee;
    @OneToOne(mappedBy = "character")
    MusicianCharacter musicianCharacter;
}
