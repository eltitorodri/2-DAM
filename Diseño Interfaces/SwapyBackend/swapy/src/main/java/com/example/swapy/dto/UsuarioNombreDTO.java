package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioNombreDTO {

    @NotBlank(message = "El nombre de usuario no puede estar vacio")
    private String nombreCompleto;

}
