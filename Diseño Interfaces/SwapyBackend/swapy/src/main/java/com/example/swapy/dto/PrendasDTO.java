package com.example.swapy.dto;

import com.example.swapy.models.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrendasDTO {

    @NotBlank(message = "Debes de indicar un titulo para la prenda ")
    private String titulo;

    @NotBlank(message = "Debes de indicar la descripcion de la prenda")
    private String descripcion;


    private LocalDate fechaAgregado;

    @NotBlank(message = "Debes de indicar si es (Prestamo) o (Intercambio)")
    private String estado;

    @NotBlank(message = "Debes de indicar como lo has guardado. (Pendiente o Guardado)")
    private String tipoGuardado;

    @NotBlank(message = "Debes de indicar alguna categoría")
    private Categorias categorias;

    @NotBlank(message = "Debes de indicar alguna marca")
    private Marcas marcas;

    @NotBlank(message = "Debes de indicar algun tipo de prendas")
    private PrendasTipos prendasTipo;

    @NotBlank(message = "Debes de indicar un id usuario")
    private Usuarios usuario;

    @NotBlank(message = "Debes de indicar algun color")
    private List<Colores> colores;

}
