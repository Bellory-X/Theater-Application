package com.example.theater.dao.repository.employee;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dao.entity.employee.Worker;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Integer> {
    @Query("""
                SELECT a
                FROM Employee e, Worker a
                WHERE e.theater = :theater
                AND e.worker = true
                AND a.idEmployee = e.id
                AND e.experience >= :exp1
                AND e.experience <= :exp2
                AND e.gender = :gender
                AND e.birthday >= :birthday1
                AND e.birthday <= :birthday2
                AND e.countChild >= :countChild1
                AND e.countChild <= :countChild2
                AND e.salary >= :salary1
                AND e.salary <= :salary2""")
    List<Worker> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1, Date birthday2,
                                int countChild1, int countChild2, int salary1, int salary2);
}