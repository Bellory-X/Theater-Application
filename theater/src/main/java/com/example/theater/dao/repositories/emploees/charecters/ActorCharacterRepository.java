package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.charecters.ActorCharacter;
import org.springframework.data.repository.CrudRepository;

public interface ActorCharacterRepository extends CrudRepository<ActorCharacter, String> {
}
