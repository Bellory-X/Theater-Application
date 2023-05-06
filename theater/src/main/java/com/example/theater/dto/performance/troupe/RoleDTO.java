package com.example.theater.dto.performance.troupe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {
    int id;
    String name;
    boolean main;
    boolean understudy;
    int idPerformance;
}
