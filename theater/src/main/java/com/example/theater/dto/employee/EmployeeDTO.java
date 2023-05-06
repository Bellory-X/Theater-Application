package com.example.theater.dto.employee;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
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
