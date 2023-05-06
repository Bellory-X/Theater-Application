package com.example.theater.dao.entity.performance.troupe;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "MusiciansPerformance")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MusiciansPerformance {
    @Id
    @Column(name = "Id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "IdPerformance")
    int idPerformance;
    @Column(name = "IdEmployee")
    int idEmployee;
}
