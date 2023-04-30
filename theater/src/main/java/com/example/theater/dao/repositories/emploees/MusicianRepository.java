package com.example.theater.dao.repositories.emploees;

import com.example.theater.dao.entities.emploees.Musician;
import org.springframework.data.repository.CrudRepository;

public interface MusicianRepository extends CrudRepository<Musician, Integer> {
}