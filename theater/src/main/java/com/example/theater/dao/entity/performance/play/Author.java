package com.example.theater.dao.entity.performance.play;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "author")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "full_name")
    String fullName;
    @Column(name = "birthday")
    Date birthday;
    @Column(name = "country")
    String country;
}
