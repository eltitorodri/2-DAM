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
@Table(name = "mensaje")

public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_envio")
    private LocalDate fechaEnvio;

    @Column(name = "contenido", length = 1000)
    private String contenido;

    @Column(name = "es_leido")
    private Boolean esLeido;

    @ManyToOne @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @ManyToOne @JoinColumn(name = "chat_id")
    private Chat chat;

}
