package com.example.swapy.dto;

import lombok.Data;

@Data
public class CrearCalificacionDTO {

    private Integer usuarioEmisorId;
    private Integer transaccionId;
    private Float rating;
    private String comentario;

}
