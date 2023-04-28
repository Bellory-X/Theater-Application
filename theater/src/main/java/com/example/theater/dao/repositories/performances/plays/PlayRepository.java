package com.example.theater.dao.repositories.performances.plays;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.performances.plays.Play;
import org.springframework.data.repository.CrudRepository;

public interface PlayRepository extends CrudRepository<Play, Integer> {
}