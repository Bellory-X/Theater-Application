package com.example.theater.dao.entity.performance;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "time_hall")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TimeHall {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "id_hall")
    int idHall;
    @Column(name = "start_data")
    Date start;
    @Column(name = "end_data")
    Date end;
    @Column(name = "id_performance")
    int idPerformance;
}
