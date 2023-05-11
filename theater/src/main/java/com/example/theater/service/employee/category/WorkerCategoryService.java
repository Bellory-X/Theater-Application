package com.example.theater.service.employee.category;

import com.example.theater.dao.entity.employee.category.WorkerCategory;
import com.example.theater.dao.repository.employee.category.WorkerCategoryRepository;
import com.example.theater.dto.employee.category.WorkerCategoryDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.category.WorkerCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class WorkerCategoryService {
    private final WorkerCategoryRepository repository;
    private final WorkerCategoryMapper mapper;

    public List<WorkerCategoryDTO> getAll() {
        Iterable<WorkerCategory> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toWorkerCategoryDTO)
                .collect(Collectors.toList());
    }

    public void add(WorkerCategoryDTO workerCategoryDTO) {
        WorkerCategory workerCategory = mapper.toNewWorkerCategory(workerCategoryDTO);
        repository.save(workerCategory);
    }

    public void edit(WorkerCategoryDTO workerCategoryDTO) {
        if (!repository.existsById(workerCategoryDTO.getId()))
            throw new RecordNotFoundException("Not found " + workerCategoryDTO.getCategory());

        WorkerCategory workerCategory = mapper.toWorkerCategory(workerCategoryDTO);
        repository.save(workerCategory);
    }

    public void drop(WorkerCategoryDTO workerCategoryDTO) {
        if (!repository.existsById(workerCategoryDTO.getId()))
            throw new RecordNotFoundException("Not found " + workerCategoryDTO.getCategory());

        WorkerCategory workerCategory = mapper.toWorkerCategory(workerCategoryDTO);
        repository.delete(workerCategory);
    }
}
