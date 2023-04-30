package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.characters.CharactersActor;
import com.example.theater.dao.entities.emploees.characters.CharactersActorKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersActorRepository extends CrudRepository<CharactersActor, CharactersActorKey> {
}
