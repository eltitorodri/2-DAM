package com.example.swapy.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearTransaccionDTO {

    @NotBlank(message = "Debes de indicar el id del user")
    private Integer usuarioSolicitanteId;

    @NotBlank(message = "Debes de indicar el id del user")
    private Integer prendaSolicitanteId;

    @NotBlank(message = "Debes de indicar el tipo de transaccion (Prestamo) o (Intercambio) ")
    private String tipoTransaccion;

    @NotBlank
    private LocalDate fechaInicio;

    @NotBlank
    private LocalDate fechaFin;


}
