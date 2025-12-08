package com.example.swapy.dto;


<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearTransaccionDTO {

<<<<<<< HEAD
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


=======
    private Integer usuarioSolicitanteId;
    private Integer prendaSolicitanteId;

    private String tipoTransaccion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
}
