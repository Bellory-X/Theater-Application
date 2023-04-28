package com.example.theater.dao.repositories.performances.plays;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.performances.plays.PlaysAuthor;
import com.example.theater.dao.entities.performances.plays.PlaysAuthorKey;
import org.springframework.data.repository.CrudRepository;

public interface PlaysAuthorRepository extends CrudRepository<PlaysAuthor, PlaysAuthorKey> {
}