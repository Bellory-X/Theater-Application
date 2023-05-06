package com.example.theater.dao.repository.performance.play;

import com.example.theater.dao.entity.performance.play.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
}