package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.characters.CharactersWorker;
import com.example.theater.dao.entities.emploees.characters.CharactersWorkerKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersWorkerRepository extends CrudRepository<CharactersWorker, CharactersWorkerKey> {
}
