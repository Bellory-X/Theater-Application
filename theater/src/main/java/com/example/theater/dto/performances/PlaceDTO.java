package com.example.theater.dto.performances;

import lombok.Value;

@Value
public class PlaceDTO {
    int id;
    int number;
    int idHall;
    int price;
    boolean reserve;
    int idSubscription;
}
