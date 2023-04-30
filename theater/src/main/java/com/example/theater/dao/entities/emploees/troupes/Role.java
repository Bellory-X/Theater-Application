package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
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
