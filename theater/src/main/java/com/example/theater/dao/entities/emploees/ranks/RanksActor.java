package com.example.theater.dao.entities.emploees.ranks;

import com.example.theater.dao.entities.emploees.Employee;
import jakarta.persistence.*;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RanksActor")
@Getter
@Setter
@IdClass(RanksActorKey.class)
public class RanksActor {
    @Id
    @Column(name = "IdEmployee")
    int id;
    @Id
    @Column(name = "IdRank")
    int idRank;
    @OneToOne(mappedBy = "id")
    Employee employee;
    @OneToOne(mappedBy = "id")
    Rank rank;
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
