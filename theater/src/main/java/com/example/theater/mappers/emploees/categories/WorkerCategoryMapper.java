package com.example.theater.mappers.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.WorkerCategory;
import com.example.theater.dto.emploees.categories.WorkerCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerCategoryMapper {
    WorkerCategory toWorkerCategory(WorkerCategoryDTO workerCategoryDTO);

    WorkerCategoryDTO toWorkerCategoryDTO(WorkerCategory workerCategory);
}
