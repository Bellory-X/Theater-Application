package com.example.theater.service.employee.category;

import com.example.theater.dao.entity.employee.category.MusicianCategory;
import com.example.theater.dao.repository.employee.category.MusicianCategoryRepository;
import com.example.theater.dto.employee.category.MusicianCategoryDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.category.MusicianCategoryMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class MusicianCategoryService {
    private final MusicianCategoryRepository repository;
    private final MusicianCategoryMapper mapper;

    public List<MusicianCategoryDTO> getAll() {
        Iterable<MusicianCategory> iterable = repository.findAll();;

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toMusicianCategoryDTO)
                .collect(Collectors.toList());
    }

    public void add(MusicianCategoryDTO dto) {
        dto.setId(Generator.generateId());
        MusicianCategory category = mapper.toMusicianCategory(dto);
        repository.save(category);
    }

    public void edit(MusicianCategoryDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCategory());

        MusicianCategory category = mapper.toMusicianCategory(dto);
        repository.save(category);
    }

    public void drop(MusicianCategoryDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getCategory());

        MusicianCategory category = mapper.toMusicianCategory(dto);
        repository.delete(category);
    }
}
