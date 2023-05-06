package com.example.theater.dao.repository.employee;

import com.example.theater.dao.entity.employee.Musician;
import org.springframework.data.repository.CrudRepository;

public interface MusicianRepository extends CrudRepository<Musician, Integer> {
}