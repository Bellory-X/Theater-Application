package com.example.theater.dao.entities.emploees;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Actor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Actor {
    @Id
    @Column(name = "IdEmployee")
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "category")
    String category;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "IdEmployee")
//    Employee employee;
//
//    @OneToMany(mappedBy = "actorCharacter", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<CharactersActor> characters;
}
