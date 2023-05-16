package com.example.theater.service.performance;

import com.example.theater.dao.entity.performance.Place;
import com.example.theater.dao.repository.performance.PlaceRepository;
import com.example.theater.dto.performance.PlaceDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.PlaceMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class PlaceService {
    private final PlaceRepository repository;
    private final PlaceMapper mapper;

    public Optional<PlaceDTO> getById(int id) {
        return repository.findById(id).map(mapper::toPlaceDTO);
    }

    public List<PlaceDTO> getAll() {
        Iterable<Place> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toPlaceDTO)
                .collect(Collectors.toList());
    }

    public void add(PlaceDTO dto) {
        dto.setId(Generator.generateId());
        Place place = mapper.toPlace(dto);
        repository.save(place);
    }

    public void edit(PlaceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Place place = mapper.toPlace(dto);
        repository.save(place);
    }

    public void drop(PlaceDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Place place = mapper.toPlace(dto);
        repository.delete(place);
    }
}
