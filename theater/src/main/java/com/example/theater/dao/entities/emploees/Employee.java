package com.example.theater.dao.entities.emploees;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
@Getter
@Setter
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "fullName")
    String fullName;

    @Column(name = "experience")
    int experience;

    @Column(name = "gender")
    String gender;

    @Column(name = "birthday")
    Date birthday;

    @Column(name = "countChild")
    int countChild;

    @Column(name = "salary")
    int salary;

    @Column(name = "isWorker")
    boolean isWorker;

    @Column(name = "theater")
    String theater;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        EmployeeEntity that = (EmployeeEntity) o;
//        if (!Objects.equals(id, that.id)) return false;
//        if (!Objects.equals(fullName, that.fullName)) return false;
//        if (!Objects.equals(experience, that.experience)) return false;
//        if (!Objects.equals(gender, that.gender)) return false;
//        if (!Objects.equals(birthday, that.birthday)) return false;
//        if (!Objects.equals(countChild, that.countChild)) return false;
//        if (!Objects.equals(salary, that.salary)) return false;
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
