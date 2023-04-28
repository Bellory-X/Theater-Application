package com.example.theater.dao.entities.emploees;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Worker")
@Getter
@Setter
public class Worker {
    @Id
    @Column(name = "IdEmployee")
    int id;
    @Column(name = "category")
    String category;
    @OneToOne(mappedBy = "id")
    Employee employee;

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
