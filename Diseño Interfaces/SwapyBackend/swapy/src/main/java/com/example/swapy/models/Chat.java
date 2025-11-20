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
@Table(name = "chat")

public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne @JoinColumn(name = "prenda_id")
    private Prendas prenda;

    @ManyToOne @JoinColumn(name = "transaccion_id")
    private Transacciones transaccion;

}
