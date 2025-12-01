package com.example.swapy.dto;

import com.example.swapy.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrendasDTO {

    private String titulo;
    private String descripcion;
    private LocalDate fechaAgregado;

    private String estado;
    private String tipoGuardado;

    private Categorias categorias;
    private Marcas marcas;
    private PrendasTipos prendasTipo;
    private Usuarios usuario;

    private List<Colores> colores;

}
