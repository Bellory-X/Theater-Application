package com.example.theater.services.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.DirectorCategory;
import com.example.theater.dao.repositories.emploees.categories.DirectorCategoryRepository;
import com.example.theater.dto.emploees.categories.DirectorCategoryDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.categories.DirectorCategoryMapper;
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
        DirectorCategory directorCategory = mapper.toDirectorCategory(directorCategoryDTO);
        repository.save(directorCategory);
    }

    public void edit(DirectorCategoryDTO directorCategoryDTO) {
        if (!repository.existsById(directorCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + directorCategoryDTO.getCategory());

        DirectorCategory directorCategory = mapper.toDirectorCategory(directorCategoryDTO);
        repository.save(directorCategory);
    }

    public void drop(DirectorCategoryDTO directorCategoryDTO) {
        if (!repository.existsById(directorCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + directorCategoryDTO.getCategory());

        DirectorCategory directorCategory = mapper.toDirectorCategory(directorCategoryDTO);
        repository.delete(directorCategory);
    }
}
