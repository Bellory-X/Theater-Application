package com.example.theater.dao.entity.performance.troupe;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "is_main")
    boolean main;
    @Column(name = "understudy")
    boolean understudy;
    @Column(name = "id_performance")
    int idPerformance;
}
