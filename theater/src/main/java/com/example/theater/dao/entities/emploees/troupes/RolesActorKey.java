package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@IdClass(RolesActorKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesActorKey implements Serializable {
    int id;
    int idEmployee;
}