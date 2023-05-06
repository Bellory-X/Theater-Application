package com.example.theater.dao.entity.employee;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Actor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Actor {
    @Id
    @Column(name = "IdEmployee")
    @EqualsAndHashCode.Include
    int idEmployee;
    @Column(name = "category")
    String category;

//    @OneToOne(fetch = FetchType.LAZY)
//    @MapsId
//    @JoinColumn(name = "IdEmployee")
//    Employee employee;

//    @OneToMany(mappedBy = "actorCharacter", cascade = CascadeType.ALL, orphanRemoval = true)
//    List<CharactersActor> characters;
}
