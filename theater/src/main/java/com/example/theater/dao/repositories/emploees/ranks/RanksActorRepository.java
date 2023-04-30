package com.example.theater.dao.repositories.emploees.ranks;

import com.example.theater.dao.entities.emploees.ranks.RanksActor;
import com.example.theater.dao.entities.emploees.ranks.RanksActorKey;
import org.springframework.data.repository.CrudRepository;

public interface RanksActorRepository extends CrudRepository<RanksActor, RanksActorKey> {
}
