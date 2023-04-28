package com.example.theater.dao.repositories.emploees;

import com.example.theater.dao.entities.emploees.Actor;
import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Integer> {
}