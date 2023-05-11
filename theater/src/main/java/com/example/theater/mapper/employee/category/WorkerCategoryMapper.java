package com.example.theater.mapper.employee.category;

import com.example.theater.dao.entity.employee.category.WorkerCategory;
import com.example.theater.dto.employee.category.WorkerCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkerCategoryMapper {
    @Mapping(target = "id", ignore = true)
    WorkerCategory toNewWorkerCategory(WorkerCategoryDTO workerCategoryDTO);

    WorkerCategory toWorkerCategory(WorkerCategoryDTO workerCategoryDTO);

    WorkerCategoryDTO toWorkerCategoryDTO(WorkerCategory workerCategory);
}
