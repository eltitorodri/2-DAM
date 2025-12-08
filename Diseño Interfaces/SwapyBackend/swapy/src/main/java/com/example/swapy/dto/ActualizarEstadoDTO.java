package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActualizarEstadoDTO {

    @NotBlank(message = "El estado ha de ser finalizada para poder valorar la transacción.")
    private String estado;
}


