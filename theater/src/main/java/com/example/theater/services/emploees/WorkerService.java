package com.example.theater.services.emploees;

import com.example.theater.dao.entities.emploees.Worker;
import com.example.theater.dao.repositories.emploees.WorkerRepository;
import com.example.theater.dto.emploees.WorkerDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.WorkerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class WorkerService {
    WorkerRepository repository;
    WorkerMapper mapper;

    public List<WorkerDTO> getAll() {
        Iterable<Worker> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toWorkerDTO)
                .collect(Collectors.toList());
    }

    public void add(WorkerDTO workerDTO) {
        Worker worker = mapper.toWorker(workerDTO);
        repository.save(worker);
    }

    public void edit(WorkerDTO workerDTO) {
        if (!repository.existsById(workerDTO.getId()))
            throw new RecordNotFoundException("Not found " + workerDTO.getId());

        Worker worker = mapper.toWorker(workerDTO);
        repository.save(worker);
    }

    public void drop(WorkerDTO workerDTO) {
        if (!repository.existsById(workerDTO.getId()))
            throw new RecordNotFoundException("Not found " + workerDTO.getId());

        Worker worker = mapper.toWorker(workerDTO);
        repository.delete(worker);
    }
}
