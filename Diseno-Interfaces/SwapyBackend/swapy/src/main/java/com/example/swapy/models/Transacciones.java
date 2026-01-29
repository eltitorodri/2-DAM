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

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transaccion", columnDefinition = "enum('Prestamo','Intercambio')")
    private TipoTransaccion tipoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "enum('Pendiente','Aceptada','Rechazada','Finalizada')")
    private EstadoTransaccion estado;

    @Column(name = "fecha_fin_real")
    private LocalDate fechaFinReal;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @ManyToOne @JoinColumn(name = "solicitante_id")
    private Usuarios solicitante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id")
    private Usuarios propietario;

}
