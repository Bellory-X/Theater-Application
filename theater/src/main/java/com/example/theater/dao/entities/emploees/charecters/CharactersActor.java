package com.example.theater.dao.entities.emploees.charecters;

import com.example.theater.dao.entities.emploees.Employee;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CharactersActor")
@Getter
@Setter
@IdClass(CharactersActorKey.class)
public class CharactersActor {
    @Id
    @Column(name = "IdEmployee")
    int id;
    @Id
    @Column(name = "character")
    String character;
    @OneToOne(mappedBy = "id")
    Employee employee;
    @OneToOne(mappedBy = "character")
    ActorCharacter actorCharacter;
}
