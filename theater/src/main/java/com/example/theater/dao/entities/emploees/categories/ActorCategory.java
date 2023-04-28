package com.example.theater.dao.entities.emploees.categories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ActorCategory")
@Getter
@Setter
public class ActorCategory {
    @Id
    @Column(name = "category")
    String category;
}
