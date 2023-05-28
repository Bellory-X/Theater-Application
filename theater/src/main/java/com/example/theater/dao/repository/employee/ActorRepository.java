package com.example.theater.dao.repository.employee;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dao.entity.performance.troupe.Role;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ActorRepository extends CrudRepository<Actor, Integer> {
    @Query("""
                SELECT a
                FROM Employee e, Actor a
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
    List<Actor> findActorQuery1(String theater, int exp1, int exp2, String gender, Date birthday1, Date birthday2,
                                int countChild1, int countChild2, int salary1, int salary2);

    @Query("""
                SELECT a
                FROM Actor a, Rank r, RanksActor ra, Employee e
                WHERE r.id = ra.idRank
                AND ra.idEmployee = e.id
                AND e.id = a.idEmployee
                AND e.theater = :theater
                AND r.data >= :dataRank1
                AND r.data <= :dataRank2
                AND r.contest = :contest
                AND e.gender = :gender
                AND e.birthday >= :birthday
                GROUP BY a.idEmployee""")
    List<Actor> findActorQuery2(String theater, Date dataRank1, Date dataRank2, String contest, String gender, Date birthday);

    @Query("""
            SELECT a
            FROM Actor a, Role r, RoleCharacter cr, CharactersActor ca, Performance p
            WHERE a.idEmployee = ca.idEmployee
            AND ca.character = cr.character
            AND cr.idRole = r.id
            AND r.id = :id
            AND p.id = r.idPerformance
            AND p.theater = a.employee.theater
            GROUP BY (a)""")
    List<Actor> findActorQuery6(int id);

    @Query("""
            SELECT r
            FROM Actor a, Role r, RolesActor ra, TimeHall th, Performance p, Play pl
            WHERE a.idEmployee = :id
            AND ra.idEmployee = a.idEmployee
            AND ra.idRole = r.id
            AND r.idPerformance = th.idPerformance
            AND th.start >= :data1
            AND th.start <= :data2
            AND p.id = r.idPerformance
            AND p.idPlay = pl.id
            GROUP BY (r)""")
    List<Role> findActorQuery10(int id, Date data1, Date data2);
}