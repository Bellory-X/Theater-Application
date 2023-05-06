package com.example.theater.dao.entity.performance.play;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Genre")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Genre {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
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
