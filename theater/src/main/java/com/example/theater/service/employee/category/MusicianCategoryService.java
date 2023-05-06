package com.example.theater.service.employee.category;

import com.example.theater.dao.entity.employee.category.MusicianCategory;
import com.example.theater.dao.repository.employee.category.MusicianCategoryRepository;
import com.example.theater.dto.employee.category.MusicianCategoryDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.category.MusicianCategoryMapper;
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

    public void add(MusicianCategoryDTO musicianCategoryDTO) {
        MusicianCategory musicianCategory = mapper.toMusicianCategory(musicianCategoryDTO);
        repository.save(musicianCategory);
    }

    public void edit(MusicianCategoryDTO musicianCategoryDTO) {
        if (!repository.existsById(musicianCategoryDTO.getId()))
            throw new RecordNotFoundException("Not found " + musicianCategoryDTO.getCategory());

        MusicianCategory musicianCategory = mapper.toMusicianCategory(musicianCategoryDTO);
        repository.save(musicianCategory);
    }

    public void drop(MusicianCategoryDTO musicianCategoryDTO) {
        if (!repository.existsById(musicianCategoryDTO.getId()))
            throw new RecordNotFoundException("Not found " + musicianCategoryDTO.getCategory());

        MusicianCategory musicianCategory = mapper.toMusicianCategory(musicianCategoryDTO);
        repository.delete(musicianCategory);
    }
}
