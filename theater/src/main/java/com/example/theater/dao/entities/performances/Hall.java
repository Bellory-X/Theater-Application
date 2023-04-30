package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Hall")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "StartData")
    Date start;
    @Column(name = "EndData")
    Date end;
    @Column(name = "Name")
    String name;
    @Column(name = "IdPerformance")
    int idPerformance;
}
