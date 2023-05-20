package com.example.theater.service.performance;

import com.example.theater.dao.entity.performance.TimeHall;
import com.example.theater.dao.repository.performance.TimeHallRepository;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.PlaceDTO;
import com.example.theater.dto.performance.TimeHallDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.performance.TimeHallMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class TimeHallService {
    private final TimeHallRepository repository;
    private final TimeHallMapper mapper;
    private final HallService hallService;
    private final PlaceService placeService;

    public Optional<TimeHallDTO> getById(int id) {
        return repository.findById(id).map(mapper::toTimeHallDTO);
    }

    public List<TimeHallDTO> getAll() {
        Iterable<TimeHall> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(el -> {
                    TimeHallDTO dto = mapper.toTimeHallDTO(el);
                    hallService.getById(dto.getIdHall())
                            .ifPresent(v -> {
                                dto.setName(v.getName());
                                dto.setTheater(v.getTheater());
                                dto.setCount(v.getCount());
                            });
                    return dto;
                }).toList();
    }

    public void add(TimeHallDTO dto) {
        try {
            dto.setId(Generator.generateId());
            TimeHall repertoire = mapper.toTimeHall(dto);
            repository.save(repertoire);
            Optional<TimeHallDTO> timeHallDTO = getAll().stream().filter(el -> el.getIdHall() == dto.getIdHall() &&
                    el.getIdPerformance() == dto.getIdPerformance()).findFirst();
            if (timeHallDTO.isPresent())
                for (int i = 0; i < dto.getCount(); i++) {
                    placeService.add(PlaceDTO.builder()
                            .idHall(timeHallDTO.get().getId())
                            .number(i + 1)
                            .price(0)
                            .reserve(false)
                            .build());
                }
        } catch (DataAccessException e) {
            throw new QueryException("Recheck fields, maybe time crosses");
        }
    }

    public void edit(TimeHallDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        TimeHall repertoire = mapper.toTimeHall(dto);
        repository.save(repertoire);
    }

    public void drop(TimeHallDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        TimeHall repertoire = mapper.toTimeHall(dto);
        repository.delete(repertoire);
    }
}
