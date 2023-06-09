package com.example.theater.service;

import com.example.theater.dao.entity.Subscription;
import com.example.theater.dao.repository.SubscriptionRepository;
import com.example.theater.dto.SubscriptionDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.exception.RecordNotFoundException;
import com.example.theater.mapper.SubscriptionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository repository;
    private final SubscriptionMapper mapper;

    public List<SubscriptionDTO> getAll() {
        Iterable<Subscription> iterable = repository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(mapper::toSubscriptionDTO)
                .collect(Collectors.toList());
    }

    public void add(SubscriptionDTO dto) {
        dto.setId(Generator.generateId());
        Subscription subscription = mapper.toSubscription(dto);
        repository.save(subscription);
    }

    public void edit(SubscriptionDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new QueryException("Not found");

        Subscription subscription = mapper.toSubscription(dto);
        repository.save(subscription);
    }

    public void drop(SubscriptionDTO dto) {
        if (!repository.existsById(dto.getId()))
            throw new QueryException("Not found");

        Subscription subscription = mapper.toSubscription(dto);
        repository.delete(subscription);
    }
}
