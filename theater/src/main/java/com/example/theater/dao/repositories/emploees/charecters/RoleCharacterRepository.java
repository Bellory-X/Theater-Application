package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.entities.emploees.charecters.RoleCharacter;
import com.example.theater.dao.entities.emploees.charecters.RoleCharacterKey;
import org.springframework.data.repository.CrudRepository;

public interface RoleCharacterRepository extends CrudRepository<RoleCharacter, RoleCharacterKey> {
}
