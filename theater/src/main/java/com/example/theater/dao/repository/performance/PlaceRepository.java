package com.example.theater.dao.repository.performance;

import com.example.theater.dao.entity.performance.Place;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PlaceRepository extends CrudRepository<Place, Integer> {
    @Query("""
                SELECT pl
                FROM Performance p, TimeHall th, Hall h, Place pl
                WHERE p.id = th.idPerformance
                AND th.idHall = h.id
                AND th.id = pl.idHall
                AND pl.reserve = true
                AND th.start >= :birthday1
                AND th.start <= :birthday2""")
    List<Place> findActorQuery11_1(Date birthday1, Date birthday2);

    @Query("""
                SELECT pl
                FROM Performance p, TimeHall th, Hall h, Place pl
                WHERE p.id = th.idPerformance
                AND th.idHall = h.id
                AND th.id = pl.idHall
                AND pl.reserve = true
                AND th.start >= :birthday1
                AND th.start <= :birthday2
                AND p.id = :id""")
    List<Place> findActorQuery11_2(Date birthday1, Date birthday2, int id);

    @Query("""
                SELECT pl
                FROM Performance p, TimeHall th, Hall h, Place pl
                WHERE p.id = th.idPerformance
                AND th.idHall = h.id
                AND th.id = pl.idHall
                AND th.start IN (SELECT MIN (th1.start)
                                 FROM TimeHall th1
                                 GROUP BY th1.idPerformance)
                AND pl.reserve = true
                AND th.start >= :birthday1
                AND th.start <= :birthday2""")
    List<Place> findActorQuery11_3(Date birthday1, Date birthday2);

    @Query("""
                SELECT pl
                FROM Performance p, TimeHall th, Hall h, Place pl
                WHERE p.id = th.idPerformance
                AND th.idHall = h.id
                AND th.id = pl.idHall
                AND pl.reserve = false
                AND th.start >= :birthday1
                AND th.start <= :birthday2""")
    List<Place> findActorQuery13_1(Date birthday1, Date birthday2);

    @Query("""
                SELECT pl
                FROM Performance p, TimeHall th, Hall h, Place pl
                WHERE p.id = th.idPerformance
                AND th.idHall = h.id
                AND th.id = pl.idHall
                AND pl.reserve = false
                AND th.start >= :birthday1
                AND th.start <= :birthday2
                AND p.id = :id""")
    List<Place> findActorQuery13_2(Date birthday1, Date birthday2, int id);

    @Query("""
                SELECT pl
                FROM Performance p, TimeHall th, Hall h, Place pl
                WHERE p.id = th.idPerformance
                AND th.idHall = h.id
                AND th.id = pl.idHall
                AND th.start IN (SELECT MIN (th1.start)
                                 FROM TimeHall th1
                                 GROUP BY th1.idPerformance)
                AND pl.reserve = false
                AND th.start >= :birthday1
                AND th.start <= :birthday2""")
    List<Place> findActorQuery13_3(Date birthday1, Date birthday2);

//    @Query("""
//                SELECT COALESCE(one.s, 0) + COALESCE(two.s, 0)
//                FROM (SELECT SUM(Места.Цена + Спектакли.Цена) AS s
//                	  FROM Performance, Залы, Места
//                	  WHERE Спектакли.Id = Залы.Id_Спектакля
//                	  AND Залы.Id = Места.Id_Зала
//                	  AND Места.Резерв = 'True'
//                	  AND Места.Id_Абонемента IS NULL
//                	  AND Залы.Начало::DATE >= '2000-10-19'
//                	  AND Залы.Начало::DATE <= '2017-10-19'
//                	  AND Спектакли.Id = 1) AS one,
//                	  (SELECT SUM(Абонементы.Цена / Абонементы.Колличество) AS s
//                	   FROM Спектакли, Залы, Места, Абонементы
//                	   WHERE Спектакли.Id = Залы.Id_Спектакля
//                	   AND Залы.Id = Места.Id_Зала
//                	   AND Места.Id_Абонемента = Абонементы.Id
//                	   AND Залы.Начало::DATE >= '2000-10-19'
//                	   AND Залы.Начало::DATE <= '2017-10-19'
//                	   AND Спектакли.Id = 1) AS two""")
//    List<Place> findActorQuery12(Date birthday1, Date birthday2);
}