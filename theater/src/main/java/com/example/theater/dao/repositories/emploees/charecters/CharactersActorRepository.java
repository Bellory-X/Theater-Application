package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.charecters.CharactersActor;
import com.example.theater.dao.entities.emploees.charecters.CharactersActorKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersActorRepository extends CrudRepository<CharactersActor, CharactersActorKey> {
}
