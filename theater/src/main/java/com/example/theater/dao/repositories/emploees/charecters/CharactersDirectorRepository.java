package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.charecters.CharactersDirector;
import com.example.theater.dao.entities.emploees.charecters.CharactersDirectorKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersDirectorRepository extends CrudRepository<CharactersDirector, CharactersDirectorKey> {
}
