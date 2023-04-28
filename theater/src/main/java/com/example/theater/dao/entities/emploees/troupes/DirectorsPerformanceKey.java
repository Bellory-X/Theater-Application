package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@IdClass(DirectorsPerformanceKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsPerformanceKey implements Serializable {
    int idPerformance;
    int idEmployee;
}