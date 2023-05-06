package com.example.theater.dao.entity.performance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "hall")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hall {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "start_data")
    Date start;
    @Column(name = "end_data")
    Date end;
    @Column(name = "name")
    String name;
    @Column(name = "id_performance")
    int idPerformance;
}
