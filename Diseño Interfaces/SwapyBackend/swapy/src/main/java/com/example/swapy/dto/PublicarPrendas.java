package com.example.swapy.dto;

import com.example.swapy.models.Colores;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicarPrendas {

    private String titulo;
    private String descripcion;
    private String estado;
    private String tipoGuardado;

    private Integer categorias;
    private Integer marcas;
    private Integer prendasTipo;
    private Integer usuario;

    private List<Integer> colores;

    private List<ImagenesDTO> imagenes;

}
