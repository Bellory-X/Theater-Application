package com.example.theater.dao.entities.emploees.charecters;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RoleCharacter")
@Getter
@Setter
@IdClass(RoleCharacterKey.class)
public class RoleCharacter {
    @Id
    @Column(name = "IdRole")
    int id;
    @Id
    @Column(name = "Character")
    String character;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
