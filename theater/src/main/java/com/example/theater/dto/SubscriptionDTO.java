package com.example.theater.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionDTO {
    int id;
    int count;
    int price;
}
