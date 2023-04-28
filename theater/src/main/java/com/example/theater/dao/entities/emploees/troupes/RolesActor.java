package com.example.theater.dao.entities.emploees.troupes;

import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RolesActor")
@Getter
@Setter
@IdClass(RolesActorKey.class)
public class RolesActor {
    @Id
    @Column(name = "IdRole")
    int id;
    @Id
    @Column(name = "IdEmployee")
    int idEmployee;

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
