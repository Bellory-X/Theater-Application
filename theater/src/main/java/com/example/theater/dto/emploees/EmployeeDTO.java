package com.example.theater.dto.emploees;

import lombok.Value;

import java.util.Date;

@Value
public class EmployeeDTO {
    int id;
    String fullName;
    int experience;
    String gender;
    Date birthday;
    int countChild;
    int salary;
    boolean isWorker;
    String theater;
}
