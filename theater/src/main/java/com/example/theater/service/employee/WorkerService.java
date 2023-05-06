package com.example.theater.service.employee;

import com.example.theater.dao.entity.employee.Worker;
import com.example.theater.dao.repository.employee.WorkerRepository;
import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.WorkerMapper;
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
        if (!repository.existsById(workerDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + workerDTO.getIdEmployee());

        Worker worker = mapper.toWorker(workerDTO);
        repository.save(worker);
    }

    public void drop(WorkerDTO workerDTO) {
        if (!repository.existsById(workerDTO.getIdEmployee()))
            throw new RecordNotFoundException("Not found " + workerDTO.getIdEmployee());

        Worker worker = mapper.toWorker(workerDTO);
        repository.delete(worker);
    }
}
