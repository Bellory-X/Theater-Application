package com.example.theater.dao.entity.performance.troupe;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role_character")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RoleCharacter {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "id_role")
    int idRole;
    @Column(name = "character")
    String character;
}
