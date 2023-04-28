package com.example.theater.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Theater")
@Getter
@Setter
public class Theater {
    @Id
    @Column(name = "Name")
    private String id;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        TheaterEntity that = (TheaterEntity) o;
//        return id != null && Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
