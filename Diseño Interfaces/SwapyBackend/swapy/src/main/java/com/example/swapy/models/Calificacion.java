package com.example.swapy.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "calificacion")

public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "comentario", length = 1000)
    private String comentario;

    @Column(name = "fecha_valoracion")
    private LocalDate fechaValoracion;

    @ManyToOne @JoinColumn(name = "transaccion_id")
    private Transacciones transaccion;

}
