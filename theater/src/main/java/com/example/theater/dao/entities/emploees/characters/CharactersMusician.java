package com.example.theater.dao.entities.emploees.characters;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CharactersMusician")
//@IdClass(CharactersMusicianKey.class)
@EqualsAndHashCode
public class CharactersMusician {
//    @Id
//    @Column(name = "IdEmployee")
//    int id;
//    @Id
//    @Column(name = "character")
//    String character;
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "IdEmployee")),
            @AttributeOverride(name = "character", column = @Column(name = "character"))
    })
    CharactersMusicianKey key;
}
