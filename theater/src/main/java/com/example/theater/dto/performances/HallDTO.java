package com.example.theater.dto.performances;

import lombok.Value;

import java.util.Date;

@Value
public class HallDTO {
    int id;
    Date start;
    Date end;
    String name;
    int idPerformance;
}
