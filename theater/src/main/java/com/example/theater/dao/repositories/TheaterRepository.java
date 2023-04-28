package com.example.theater.dao.repositories;

import com.example.theater.dao.entities.Theater;
import org.springframework.data.repository.CrudRepository;

public interface TheaterRepository extends CrudRepository<Theater, String> {
}