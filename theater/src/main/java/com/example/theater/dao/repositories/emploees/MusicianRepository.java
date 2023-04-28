package com.example.theater.dao.repositories.emploees;

import com.example.theater.dao.entities.emploees.Musician;
import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;

public interface MusicianRepository extends CrudRepository<Musician, Integer> {
}