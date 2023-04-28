package com.example.theater.dao.entities.emploees.ranks;

import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@IdClass(RanksActorKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RanksActorKey implements Serializable {
    int id;
    int idRank;
}