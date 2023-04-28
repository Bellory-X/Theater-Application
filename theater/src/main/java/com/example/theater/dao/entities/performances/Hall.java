package com.example.theater.dao.entities.performances;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Hall")
@Getter
@Setter
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
