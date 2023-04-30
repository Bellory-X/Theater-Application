package com.example.theater.dao.entities.performances.plays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Genre")
@EqualsAndHashCode
public class Genre {
    @Id
    @Column(name = "Name")
    String name;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        GenreEntity that = (GenreEntity) o;
//        return name != null && Objects.equals(name, that.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
