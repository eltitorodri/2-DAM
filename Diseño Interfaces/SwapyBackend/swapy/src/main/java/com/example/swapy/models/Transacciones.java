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
@Table(name = "transacciones")

public class Transacciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_transaccion", length = 50)
    private String tipoTransaccion;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "fecha_fin_real")
    private LocalDate fechaFinReal;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @ManyToOne @JoinColumn(name = "solicitante_id")
    private Usuarios solicitante;

    @ManyToOne @JoinColumn(name = "propietario_id")
    private Usuarios propietario;

}
