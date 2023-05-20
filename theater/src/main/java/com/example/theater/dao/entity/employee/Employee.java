package com.example.theater.dao.entity.employee;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employee")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "experience")
    int experience;
    @Column(name = "gender")
    String gender;
    @Column(name = "birthday")
    Date birthday;
    @Column(name = "count_child")
    int countChild;
    @Column(name = "salary")
    int salary;
    @Column(name = "is_worker")
    boolean worker;
    @Column(name = "theater")
    String theater;
}
