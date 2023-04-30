package com.example.theater.mappers.emploees;

import com.example.theater.dao.entities.emploees.Worker;
import com.example.theater.dto.emploees.WorkerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkerMapper {
    Worker toWorker(WorkerDTO workerDTO);

    WorkerDTO toWorkerDTO(Worker worker);
}
