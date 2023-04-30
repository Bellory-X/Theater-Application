package com.example.theater.mappers.performances;

import com.example.theater.dao.entities.performances.Subscription;
import com.example.theater.dto.performances.SubscriptionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    Subscription toSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO toSubscriptionDTO(Subscription subscription);
}
