package com.example.theater.dao.repositories.emploees.troupes;

import com.example.theater.dao.entities.emploees.troupes.RolesActor;
import com.example.theater.dao.entities.emploees.troupes.RolesActorKey;
import org.springframework.data.repository.CrudRepository;

public interface RolesActorRepository extends CrudRepository<RolesActor, RolesActorKey> {
}