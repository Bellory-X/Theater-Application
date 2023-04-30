package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.characters.CharactersMusician;
import com.example.theater.dao.entities.emploees.characters.CharactersMusicianKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersMusicianRepository extends CrudRepository<CharactersMusician, CharactersMusicianKey> {
}
