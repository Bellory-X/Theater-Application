package com.example.theater.dto.emploees.troupes;

import lombok.Value;

@Value
public class RoleDTO {
    int id;
    int count;
    boolean main;
    boolean understudy;
    int idPerformance;
}
