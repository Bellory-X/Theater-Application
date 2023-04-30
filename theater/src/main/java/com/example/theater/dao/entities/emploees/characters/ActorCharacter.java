package com.example.theater.dao.entities.emploees.characters;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ActorCharacter")
@EqualsAndHashCode
public class ActorCharacter {
    @Id
    @Column(name = "character")
    String character;
}
