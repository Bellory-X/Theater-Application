package com.example.theater.dto.performance.troupe;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolesActorDTO {
    int id;
    int idRole;
    int idEmployee;
}
