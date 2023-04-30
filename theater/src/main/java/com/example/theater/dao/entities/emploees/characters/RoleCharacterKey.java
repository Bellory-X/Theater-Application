package com.example.theater.dao.entities.emploees.characters;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@IdClass(RoleCharacterKey.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RoleCharacterKey implements Serializable {
    int id;
    String character;
}