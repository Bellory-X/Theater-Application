package com.example.theater.dao.repository.performance.play;

import com.example.theater.dao.entity.performance.play.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
}