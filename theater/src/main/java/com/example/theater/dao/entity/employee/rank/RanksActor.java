package com.example.theater.dao.entity.employee.rank;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "RanksActor")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RanksActor {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "IdEmployee")
    int idEmployee;
    @Column(name = "IdRank")
    int idRank;
}
