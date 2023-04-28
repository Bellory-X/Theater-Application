package com.example.theater.dao.repositories.emploees;

import com.example.theater.dao.entities.emploees.Director;
import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<Director, Integer> {
}