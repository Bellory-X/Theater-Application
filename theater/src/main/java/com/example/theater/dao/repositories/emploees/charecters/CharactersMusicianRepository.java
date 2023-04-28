package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.charecters.CharactersMusician;
import com.example.theater.dao.entities.emploees.charecters.CharactersMusicianKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersMusicianRepository extends CrudRepository<CharactersMusician, CharactersMusicianKey> {
}
