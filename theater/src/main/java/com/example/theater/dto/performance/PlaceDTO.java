package com.example.theater.dto.performance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceDTO {
    int id;
    int number;
    int idHall;
    int price;
    boolean reserve;
    Integer idSubscription;
}
