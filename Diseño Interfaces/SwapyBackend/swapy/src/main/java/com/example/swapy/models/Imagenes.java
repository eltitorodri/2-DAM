package com.example.swapy.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "imagenes")

public class Imagenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "orden", nullable = true)
    private int orden;

    @Column(name = "url_imagen", length = 255)
    private String url_imagen;

    @ManyToOne @JoinColumn(name = "prendas_id")
    private Prendas prenda;

}
