package com.example.theater.dao.repository.performance;

import com.example.theater.dao.entity.employee.Actor;
import com.example.theater.dao.entity.performance.Performance;
import com.example.theater.dao.entity.performance.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PerformanceRepository extends CrudRepository<Performance, Integer> {
    @Query("""
            SELECT p
            FROM Play pl, Performance p, TimeHall th, Hall h
            WHERE pl.id = p.idPlay
            AND pl.genre = :genre
            AND p.id = th.idPerformance
            AND h.id = th.idHall
            AND h.theater = :theater
            AND th.start >= :birthday1
            AND th.start <= :birthday2
            GROUP BY (p)""")
    List<Performance> findActorQuery2(Date birthday1, Date birthday2, String genre, String theater);

    @Query("""
            SELECT p
            FROM Play pl, Performance p, TimeHall th
            WHERE pl.id = p.idPlay
            AND pl.genre = :genre
            AND p.theater = :theater
            AND p.id = th.idPerformance
            AND th.start >= :birthday1
            AND th.start <= :birthday2""")
    List<Performance> findActorQuery3(Date birthday1, Date birthday2, String genre, String theater);

    @Query("""
            SELECT p
            FROM Play pl, Performance p, Author a, PlaysAuthor pa, TimeHall th
            WHERE pl.id = p.idPlay
            AND pl.genre = :genre
            AND pl.id = pa.idPlay
            AND pa.idAuthor = a.id
            AND a.country = :country
            AND a.fullName = :fullName
            AND EXTRACT(YEAR FROM pl.data) >= :year1
            AND EXTRACT(YEAR FROM pl.data) <= :year2
            AND p.theater = :theater
            AND p.id = th.idPerformance
            AND th.start >= :birthday1
            AND th.start <= :birthday2""")
    List<Performance> findActorQuery5(Date birthday1, Date birthday2, String genre, String theater, String country,
                                      String fullName, int year1, int year2);

    @Query("""
            SELECT p
            FROM Performance p, TimeHall th, Hall h
            WHERE p.id = th.idPerformance
            AND th.idHall = h.id
            AND NOT h.theater = p.theater
            AND th.start >= :birthday1
            AND th.start <= :birthday2
            GROUP BY (p)""")
    List<Performance> findActorQuery8(Date birthday1, Date birthday2);
}