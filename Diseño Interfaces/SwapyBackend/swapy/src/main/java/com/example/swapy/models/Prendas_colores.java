package com.example.swapy.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "prendas_colores")

public class Prendas_colores {

    @ManyToMany @JoinColumn(name = "prendas_id")
    private List<Prendas> prendas;

    @ManyToMany @JoinColumn(name = "colores_id")
    private List<Colores> colores;

}
