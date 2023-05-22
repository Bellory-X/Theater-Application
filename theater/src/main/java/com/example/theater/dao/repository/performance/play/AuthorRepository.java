package com.example.theater.dao.repository.performance.play;

import com.example.theater.dao.entity.performance.play.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
    @Query("""
            SELECT a
            FROM Performance p, Play pl, Author a, TimeHall th, PlaysAuthor pa
            WHERE pl.id = p.idPlay
            AND p.id = th.idPerformance
            AND pa.idAuthor = a.id
            AND pa.idPlay = pl.id
            AND EXTRACT(YEAR FROM a.birthday) >= :start1
            AND EXTRACT(YEAR FROM a.birthday) <= :start2
            AND a.country = :country
            AND pl.genre = :genre
            AND p.theater = :theater
            AND th.start >= :birthday1
            AND th.start <= :birthday2
            GROUP BY a""")
    List<Author> findActorQuery4(Date birthday1, Date birthday2, String genre, String theater, String country,
                                      int start1, int start2);
}