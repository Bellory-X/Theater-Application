package com.example.theater.dao.repository;

import com.example.theater.dao.entity.Theater;
import org.springframework.data.repository.CrudRepository;

public interface TheaterRepository extends CrudRepository<Theater, Integer> {
}