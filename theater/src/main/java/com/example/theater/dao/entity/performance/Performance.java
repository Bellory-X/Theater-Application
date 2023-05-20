package com.example.theater.dao.entity.performance;

import com.example.theater.dao.entity.performance.play.Play;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "performance")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Performance {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    int id;
    @Column(name = "id_play")
    int idPlay;
//    @Column(name = "id_repertoire")
//    int idRepertoire;
    @Column(name = "price")
    int price;
    @Column(name = "theater")
    String theater;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @MapsId
//    @JoinColumn(name = "id_play")
//    Play play;
}
