package com.example.theater.mapper;

import com.example.theater.dao.entity.Subscription;
import com.example.theater.dto.SubscriptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "id", ignore = true)
    Subscription toNewSubscription(SubscriptionDTO subscriptionDTO);

    Subscription toSubscription(SubscriptionDTO subscriptionDTO);

    SubscriptionDTO toSubscriptionDTO(Subscription subscription);
}
