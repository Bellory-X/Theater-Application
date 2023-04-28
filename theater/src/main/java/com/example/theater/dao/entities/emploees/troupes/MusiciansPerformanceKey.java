package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@IdClass(MusiciansPerformanceKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MusiciansPerformanceKey implements Serializable {
    int idPerformance;
    int idEmployee;
}