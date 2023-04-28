package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MusiciansPerformance")
@Getter
@Setter
@IdClass(MusiciansPerformanceKey.class)
public class MusiciansPerformance {
    @Id
    @Column(name = "IdEmployee")
    int idEmployee;

    @Column(name = "IdPerformance")
    int idPerformance;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
