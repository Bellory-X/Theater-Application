package com.example.theater.dao.entity.performance.troupe;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RoleCharacter")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleCharacter {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "IdRole")
    int idRole;
    @Column(name = "character")
    String character;
}
