package com.example.theater.dao.entities.performances.plays;

import com.example.theater.dao.entities.emploees.troupes.RolesActorKey;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PlaysAuthor")
//@IdClass(PlaysAuthorKey.class)
@EqualsAndHashCode
public class PlaysAuthor {
//    @Id
//    @Column(name = "IdAuthor")
//    int idAuthor;
//    @Id
//    @Column(name = "IdPlay")
//    int idPlay;
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "idAuthor", column = @Column(name = "IdAuthor")),
            @AttributeOverride(name = "idPlay", column = @Column(name = "IdPlay"))
    })
    PlaysAuthorKey key;
}
