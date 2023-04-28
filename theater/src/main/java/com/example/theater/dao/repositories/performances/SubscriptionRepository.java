package com.example.theater.dao.repositories.performances;

import com.example.theater.dao.entities.performances.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
}