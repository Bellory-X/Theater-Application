package com.example.theater.mapper.employee.category;

import com.example.theater.dao.entity.employee.category.ActorCategory;
import com.example.theater.dto.employee.category.ActorCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorCategoryMapper {
    @Mapping(target = "id", ignore = true)
    ActorCategory toNewActorCategory(ActorCategoryDTO actorCategoryDTO);

    ActorCategory toActorCategory(ActorCategoryDTO actorCategoryDTO);

    ActorCategoryDTO toActorCategoryDTO(ActorCategory actorCategory);
}
