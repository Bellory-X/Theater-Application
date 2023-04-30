package com.example.theater.dao.entities.emploees.categories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "WorkerCategory")
@EqualsAndHashCode
public class WorkerCategory {
    @Id
    @Column(name = "category")
    String category;
}
