package com.example.theater.dao.entities.emploees.characters;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CharactersWorker")
//@IdClass(CharactersWorkerKey.class)
@EqualsAndHashCode
public class CharactersWorker {
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
    CharactersWorkerKey key;
}
