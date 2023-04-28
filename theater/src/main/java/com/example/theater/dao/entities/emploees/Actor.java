package com.example.theater.dao.entities.emploees;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Actor")
@Getter
@Setter
public class Actor {
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
//        EmployeeEntity that = (EmployeeEntity) o;
//        if (!Objects.equals(isWorker, that.isWorker)) return false;
//
//        return Objects.equals(theater, that.theater);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
