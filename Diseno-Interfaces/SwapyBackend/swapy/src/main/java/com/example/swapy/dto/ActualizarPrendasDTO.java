package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import jakarta.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarPrendasDTO {

    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(max = 150, message = "El titulo es demasiado largo")
    private String titulo;

    @NotBlank(message = "La descripcion no puede estar vacia")
    @Size(max = 1000, message = "La descripcion es demasiado larga")
    private String descripcion;

    @NotBlank(message = "Debes de indicar el estado (Prestamo/Intercambio)")
    @Size(max = 12, message = "Estado excede el limite de la DB")
    private String estado;

    @NotBlank(message = "Debes de indicar el tipo de guardado")
    @Size(max = 9, message = "Tipo de Guardado excede el limite de la DB")
    private String tipoGuardado;

    @NotNull(message = "Debes de indicar una categoría")
    private Integer categorias;

    @NotNull(message = "Debes de indicar una marca")
    private Integer marcas;

    @NotNull(message = "Debes de indicar un tipo de prenda")
    private Integer prendasTipo;

    @Nullable
    private Integer usuario;

    @Nullable
    private Integer imagen;

    @NotNull(message = "Debes de indicar algun color")
    private List<Integer> colores;
}