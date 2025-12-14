package com.example.swapy.dto;

import com.example.swapy.models.Imagenes;
import com.example.swapy.models.Marcas;
import com.example.swapy.models.Usuarios;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MostrarItemPrendaDTO {

    @NotBlank(message = "Debes de indicar un id de alguna imagen")
    private String  imagenUrl;

    @NotBlank(message = "Debes de indicar un titulo para la prenda ")
    private String titulo;

    private String usuario;

    @NotBlank(message = "Debes de indicar alguna marca")
    private String nombreMarca;

}
