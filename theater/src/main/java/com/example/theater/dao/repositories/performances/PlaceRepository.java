package com.example.theater.dao.repositories.performances;

import com.example.theater.dao.entities.performances.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, Integer> {
}