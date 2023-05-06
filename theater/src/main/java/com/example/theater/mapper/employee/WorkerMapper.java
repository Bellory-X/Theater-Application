package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Worker;
import com.example.theater.dto.employee.WorkerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkerMapper {
    @Mapping(target = "employee", ignore = true)
    Worker toWorker(WorkerDTO workerDTO);

    @Mapping(target = "fullName", source = "employee.fullName")
    @Mapping(target = "experience", source = "employee.experience")
    @Mapping(target = "gender", source = "employee.gender")
    @Mapping(target = "birthday", source = "employee.birthday")
    @Mapping(target = "countChild", source = "employee.countChild")
    @Mapping(target = "salary", source = "employee.salary")
    @Mapping(target = "worker", source = "employee.worker")
    @Mapping(target = "theater", source = "employee.theater")
    WorkerDTO toWorkerDTO(Worker worker);
}
