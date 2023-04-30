package com.example.theater.services.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.WorkerCategory;
import com.example.theater.dao.repositories.emploees.categories.WorkerCategoryRepository;
import com.example.theater.dto.emploees.categories.WorkerCategoryDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.categories.WorkerCategoryMapper;
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
        WorkerCategory workerCategory = mapper.toWorkerCategory(workerCategoryDTO);
        repository.save(workerCategory);
    }

    public void edit(WorkerCategoryDTO workerCategoryDTO) {
        if (!repository.existsById(workerCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + workerCategoryDTO.getCategory());

        WorkerCategory workerCategory = mapper.toWorkerCategory(workerCategoryDTO);
        repository.save(workerCategory);
    }

    public void drop(WorkerCategoryDTO workerCategoryDTO) {
        if (!repository.existsById(workerCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + workerCategoryDTO.getCategory());

        WorkerCategory workerCategory = mapper.toWorkerCategory(workerCategoryDTO);
        repository.delete(workerCategory);
    }
}
