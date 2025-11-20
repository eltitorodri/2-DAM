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
@Table(name = "prendas")

public class Prendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "descripcion",length = 1000, nullable = false)
    private String descripcion;

    @Column(name = "fecha_agregado")
    private LocalDate fechaAgregado;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "tipoGuardado", nullable = false)
    private String tipoGuardado;

    @ManyToOne @JoinColumn(name = "categorias_id")
    private Categorias categorias;

    @ManyToOne @JoinColumn(name = "marcas_id")
    private Marcas marcas;

    @ManyToOne @JoinColumn(name = "prendas_tipo_id")
    private Prendas prendasTipo;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

}
