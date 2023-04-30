package com.example.theater.mappers.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dto.emploees.categories.ActorCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorCategoryMapper {
    ActorCategory toActorCategory(ActorCategoryDTO actorCategoryDTO);

    ActorCategoryDTO toActorCategoryDTO(ActorCategory actorCategory);
}
