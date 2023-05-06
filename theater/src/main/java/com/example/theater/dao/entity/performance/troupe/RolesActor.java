package com.example.theater.dao.entity.performance.troupe;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RolesActor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RolesActor {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "IdRole")
    int idRole;
    @Column(name = "IdEmployee")
    int idEmployee;
}
