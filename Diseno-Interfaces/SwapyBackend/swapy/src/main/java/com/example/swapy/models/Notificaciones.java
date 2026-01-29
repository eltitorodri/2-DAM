package com.example.swapy.models;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "notificaciones")

public class Notificaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "mensaje", length = 1000)
    private String mensaje;

    @Column(name = "es_leido")
    private boolean esLeido;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @ManyToOne @JoinColumn(name = "chat_id")
    private Chat chat;

}
