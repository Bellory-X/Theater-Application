package com.example.theater.dao.entity.performance.troupe;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "Name")
    int count;
    @Column(name = "IsMain")
    boolean main;
    @Column(name = "Understudy")
    boolean understudy;
    @Column(name = "IdPerformance")
    int idPerformance;
}
