package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DirectorsPerformance")
@Getter
@Setter
@IdClass(DirectorsPerformanceKey.class)
public class DirectorsPerformance {
    @Id
    @Column(name = "IdPerformance")
    int idPerformance;
    @Id
    @Column(name = "IdEmployee")
    int idEmployee;
}
