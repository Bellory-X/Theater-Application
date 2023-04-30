package com.example.theater.services.emploees.categories;

import com.example.theater.dao.entities.emploees.categories.ActorCategory;
import com.example.theater.dao.repositories.emploees.categories.ActorCategoryRepository;
import com.example.theater.dto.emploees.categories.ActorCategoryDTO;
import com.example.theater.exceptions.RecordNotFoundException;
import com.example.theater.mappers.emploees.categories.ActorCategoryMapper;
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

    public void add(ActorCategoryDTO actorCategoryDTO) {
        ActorCategory actorCategory = mapper.toActorCategory(actorCategoryDTO);
        repository.save(actorCategory);
    }

    public void edit(ActorCategoryDTO actorCategoryDTO) {
        if (!repository.existsById(actorCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + actorCategoryDTO.getCategory());

        ActorCategory actorCategory = mapper.toActorCategory(actorCategoryDTO);
        repository.save(actorCategory);
    }

    public void drop(ActorCategoryDTO actorCategoryDTO) {
        if (!repository.existsById(actorCategoryDTO.getCategory()))
            throw new RecordNotFoundException("Not found " + actorCategoryDTO.getCategory());

        ActorCategory actorCategory = mapper.toActorCategory(actorCategoryDTO);
        repository.delete(actorCategory);
    }
}
