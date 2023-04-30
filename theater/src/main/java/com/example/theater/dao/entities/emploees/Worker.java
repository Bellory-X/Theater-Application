package com.example.theater.dao.entities.emploees;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Worker")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Worker {
    @Id
    @Column(name = "IdEmployee")
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "category")
    String category;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "IdEmployee")
//    Employee employee;

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
