package com.example.theater.mapper;

import com.example.theater.dao.entity.Subscription;
import com.example.theater.dto.SubscriptionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {
    Subscription toSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO toSubscriptionDTO(Subscription subscription);
}
