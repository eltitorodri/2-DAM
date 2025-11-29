package com.example.swapy.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearTransaccionDTO {

    private Integer usuarioSolicitanteId;
    private Integer prendaSolicitanteId;

    private String tipoTransaccion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

}
