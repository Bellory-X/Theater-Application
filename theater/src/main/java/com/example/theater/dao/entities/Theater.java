package com.example.theater.dao.entities;

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
@Table(name = "Theater")
@EqualsAndHashCode
public class Theater {
    @Id
    @Column(name = "Name")
    private String id;
}
