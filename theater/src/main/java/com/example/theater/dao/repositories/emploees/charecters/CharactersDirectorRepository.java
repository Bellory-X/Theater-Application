package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.characters.CharactersDirector;
import com.example.theater.dao.entities.emploees.characters.CharactersDirectorKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersDirectorRepository extends CrudRepository<CharactersDirector, CharactersDirectorKey> {
}
