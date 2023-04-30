package com.example.theater.dto.performances;

import lombok.Value;

@Value
public class PerformanceDTO {
    int id;
    int idPlay;
    int idRepertoire;
    int price;
    String theater;
}
