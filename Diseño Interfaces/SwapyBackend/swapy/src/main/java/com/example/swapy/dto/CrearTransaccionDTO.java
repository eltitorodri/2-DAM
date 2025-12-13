package com.example.swapy.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CrearTransaccionDTO {

    @NotNull(message = "Debes de indicar el id del user")
    private Integer usuarioSolicitanteId;

    @NotNull(message = "Debes de indicar el id del user")
    private Integer prendaSolicitanteId;

    @NotBlank(message = "Debes de indicar el tipo de transaccion (Prestamo) o (Intercambio) ")
    private String tipoTransaccion;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

}
