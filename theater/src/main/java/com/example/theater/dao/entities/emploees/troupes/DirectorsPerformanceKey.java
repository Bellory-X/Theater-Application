package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.Embeddable;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
//@IdClass(DirectorsPerformanceKey.class)
public class DirectorsPerformanceKey implements Serializable {
    int idPerformance;
    int idEmployee;
}