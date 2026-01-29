package com.example.swapy.dto;

import com.example.swapy.models.EstadoTransaccion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActualizarEstadoDTO {
    @NotNull(message = "El estado ha de ser finalizada para poder valorar la transacción.")
    private EstadoTransaccion nuevoEstado;
}

