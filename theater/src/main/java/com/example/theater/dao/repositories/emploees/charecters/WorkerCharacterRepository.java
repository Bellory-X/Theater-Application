package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.charecters.WorkerCharacter;
import org.springframework.data.repository.CrudRepository;

public interface WorkerCharacterRepository extends CrudRepository<WorkerCharacter, String> {
}
