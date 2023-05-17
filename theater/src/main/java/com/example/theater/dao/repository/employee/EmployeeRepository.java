package com.example.theater.dao.repository.employee;

import com.example.theater.dao.entity.employee.Employee;
import com.example.theater.dao.entity.employee.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Query("""
                SELECT e
                FROM Employee e
                WHERE e.theater = :theater
                AND e.worker = true
                AND e.experience >= :exp1
                AND e.experience <= :exp2
                AND e.gender = :gender
                AND e.birthday >= :birthday1
                AND e.birthday <= :birthday2
                AND e.countChild >= :countChild1
                AND e.countChild <= :countChild2
                AND e.salary >= :salary1
                AND e.salary <= :salary2""")
    List<Employee> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1, Date birthday2,
                                 int countChild1, int countChild2, int salary1, int salary2);
}