package com.example.theater.dao.repositories.emploees.charecters;

import com.example.theater.dao.entities.emploees.characters.RoleCharacter;
import com.example.theater.dao.entities.emploees.characters.RoleCharacterKey;
import org.springframework.data.repository.CrudRepository;

public interface RoleCharacterRepository extends CrudRepository<RoleCharacter, RoleCharacterKey> {
}
