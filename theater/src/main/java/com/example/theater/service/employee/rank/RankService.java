package com.example.theater.service.employee.rank;

import com.example.theater.dao.entity.employee.rank.Rank;
import com.example.theater.dao.repository.employee.rank.RankRepository;
import com.example.theater.dto.employee.rank.RankDTO;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.employee.rank.RankMapper;
import com.example.theater.service.Generator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class RankService {
    private final RankRepository repository;
    private final RankMapper mapper;

    public Optional<RankDTO> getById(int id) {
        return repository.findById(id).map(mapper::toRankDTO);
    }

    public List<RankDTO> getAll() {
        Iterable<Rank> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toRankDTO)
                .collect(Collectors.toList());
    }

    public void add(RankDTO dto) {
        dto.setId(Generator.generateId());
        Rank rank = mapper.toRank(dto);
        repository.save(rank);
    }

    public void edit(RankDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Rank rank = mapper.toRank(dto);
        repository.save(rank);
    }

    public void drop(RankDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new RecordNotFoundException("Not found " + dto.getId());

        Rank rank = mapper.toRank(dto);
        repository.delete(rank);
    }
}
