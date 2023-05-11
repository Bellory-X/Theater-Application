package com.example.theater.service.employee.category;

import com.example.theater.dao.entity.employee.category.DirectorCategory;
import com.example.theater.dao.repository.employee.category.DirectorCategoryRepository;
import com.example.theater.dto.employee.category.DirectorCategoryDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.category.DirectorCategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DirectorCategoryService {
    private final DirectorCategoryRepository repository;
    private final DirectorCategoryMapper mapper;

    public List<DirectorCategoryDTO> getAll() {
        Iterable<DirectorCategory> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toDirectorCategoryDTO)
                .collect(Collectors.toList());
    }

    public void add(DirectorCategoryDTO directorCategoryDTO) {
        DirectorCategory directorCategory = mapper.toNewDirectorCategory(directorCategoryDTO);
        repository.save(directorCategory);
    }

    public void edit(DirectorCategoryDTO directorCategoryDTO) {
        if (!repository.existsById(directorCategoryDTO.getId()))
            throw new RecordNotFoundException("Not found " + directorCategoryDTO.getCategory());

        DirectorCategory directorCategory = mapper.toDirectorCategory(directorCategoryDTO);
        repository.save(directorCategory);
    }

    public void drop(DirectorCategoryDTO directorCategoryDTO) {
        if (!repository.existsById(directorCategoryDTO.getId()))
            throw new RecordNotFoundException("Not found " + directorCategoryDTO.getCategory());

        DirectorCategory directorCategory = mapper.toDirectorCategory(directorCategoryDTO);
        repository.delete(directorCategory);
    }
}
