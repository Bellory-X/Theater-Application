package com.example.theater.dao.entities.performances.plays;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Author")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Author {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "FullName")
    String fullName;
    @Column(name = "Birthday")
    Date birthday;
    @Column(name = "Country")
    String country;
}
