package com.example.theater.mapper.employee;

import com.example.theater.dao.entity.employee.Worker;
import com.example.theater.dto.employee.WorkerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerMapper {
    Worker toWorker(WorkerDTO workerDTO);

    WorkerDTO toWorkerDTO(Worker worker);
}
