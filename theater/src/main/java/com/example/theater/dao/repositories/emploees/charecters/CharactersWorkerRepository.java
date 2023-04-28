package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.charecters.CharactersWorker;
import com.example.theater.dao.entities.emploees.charecters.CharactersWorkerKey;
import org.springframework.data.repository.CrudRepository;

public interface CharactersWorkerRepository extends CrudRepository<CharactersWorker, CharactersWorkerKey> {
}
