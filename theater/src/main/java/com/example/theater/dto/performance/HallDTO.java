package com.example.theater.dto.performance;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HallDTO {
    int id;
    String name;
    String theater;
    int count;
}
