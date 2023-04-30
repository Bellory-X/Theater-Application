package com.example.theater.dao.entities.emploees.categories;

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
@Table(name = "ActorCategory")
@EqualsAndHashCode
public class ActorCategory {
    @Id
    @Column(name = "category")
    String category;
}
