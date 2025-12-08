package com.example.swapy.dto;

import com.example.swapy.models.Colores;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicarPrendas {

    @NotBlank(message = "Debes de indicar un titulo para la prenda ")
    private String titulo;

    @NotBlank(message = "Debes de indicar la descripcion de la prenda")
    private String descripcion;

    @NotBlank(message = "Debes de indicar si es (Prestamo) o (Intercambio)")
    private String estado;

    @NotBlank(message = "Debes de indicar como lo has guardado. (Pendiente o Guardado)")
    private String tipoGuardado;

    @NotNull(message = "Debes de indicar alguna categoría")
    private Integer categorias;

    @NotNull(message = "Debes de indicar alguna marca")
    private Integer marcas;

    @NotNull(message = "Debes de indicar algun tipo de prendas")
    private Integer prendasTipo;

    @NotNull(message = "Debes de indicar un id usuario")
    private Integer usuario;

    @NotNull(message = "Debes de indicar algun color")
    private List<Integer> colores;

}
