package com.example.theater.dao.entity.performance.troupe;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "musicians_performance")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MusiciansPerformance {
    @Id
    @Column(name = "id")
    @EqualsAndHashCode.Include
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "id_performance")
    int idPerformance;
    @Column(name = "id_employee")
    int idEmployee;
}
