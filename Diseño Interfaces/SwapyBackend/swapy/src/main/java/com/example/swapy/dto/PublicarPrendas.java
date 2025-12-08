package com.example.swapy.dto;

import com.example.swapy.models.Colores;
<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
=======
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicarPrendas {

<<<<<<< HEAD
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
=======
    private String titulo;
    private String descripcion;
    private String estado;
    private String tipoGuardado;

    private Integer categorias;
    private Integer marcas;
    private Integer prendasTipo;
    private Integer usuario;

>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
    private List<Integer> colores;

}
