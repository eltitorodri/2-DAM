package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioActivosDTO {


    @NotBlank(message = "Debes de indicar tu nombre completo")
    private String nombreCompleto;

    private Long numeroIntercambios;

}
