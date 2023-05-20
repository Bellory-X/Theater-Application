package com.example.theater.dto.performance;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TimeHallDTO {
    int id;
    int idHall;
    Date start;
    Date end;
    int idPerformance;
    String name;
    String theater;
    int count;
}
