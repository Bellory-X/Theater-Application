package com.example.theater.dto.employee;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActorDTO {
    int idEmployee;
    String category;
}