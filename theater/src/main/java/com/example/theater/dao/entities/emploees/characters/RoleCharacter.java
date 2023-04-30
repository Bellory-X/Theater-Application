package com.example.theater.dao.entities.emploees.characters;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RoleCharacter")
//@IdClass(RoleCharacterKey.class)
@EqualsAndHashCode
public class RoleCharacter {
//    @Id
//    @Column(name = "IdRole")
//    int id;
//    @Id
//    @Column(name = "Character")
//    String character;
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "IdRole")),
            @AttributeOverride(name = "character", column = @Column(name = "Character"))
    })
    RoleCharacterKey key;
}
