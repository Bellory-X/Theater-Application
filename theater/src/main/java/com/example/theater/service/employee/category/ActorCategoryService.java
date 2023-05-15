package com.example.theater.service.employee.category;

import com.example.theater.dao.entity.employee.category.ActorCategory;
import com.example.theater.dao.repository.employee.category.ActorCategoryRepository;
import com.example.theater.dto.employee.category.ActorCategoryDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.category.ActorCategoryMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ActorCategoryService {
    private final ActorCategoryRepository repository;
    private final ActorCategoryMapper mapper;

    public List<ActorCategoryDTO> getAll() {
        Iterable<ActorCategory> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toActorCategoryDTO)
                .collect(Collectors.toList());
    }

    public void add(ActorCategoryDTO dto) {
        dto.setId(Generator.generateId());
        ActorCategory category = mapper.toActorCategory(dto);
        repository.save(category);
    }

    public void edit(ActorCategoryDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCategory());

        ActorCategory category = mapper.toActorCategory(dto);
        repository.save(category);
    }

    public void drop(ActorCategoryDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCategory());

        ActorCategory category = mapper.toActorCategory(dto);
        repository.delete(category);
    }
}
