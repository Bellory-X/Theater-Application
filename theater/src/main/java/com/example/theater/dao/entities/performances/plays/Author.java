package com.example.theater.dao.entities.performances.plays;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Author")
@Getter
@Setter
public class Author {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "FullName")
    String fullName;
    @Column(name = "Birthday")
    Date birthday;
    @Column(name = "Country")
    String country;
}
