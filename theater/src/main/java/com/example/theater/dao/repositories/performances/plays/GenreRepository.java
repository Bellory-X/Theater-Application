package com.example.theater.dao.repositories.performances.plays;

import com.example.theater.dao.entities.performances.plays.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, String> {
}