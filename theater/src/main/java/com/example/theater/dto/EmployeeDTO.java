package com.example.theater.dto;

import lombok.Value;

@Value
public class EmployeeDTO {
    int id;
    String fullName;
    int experience;
    String gender;

    int countChild;
    int salary;
    boolean isWorker;
    String theater;
}
