package com.example.swapy.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CalificacionResponseDTO {
    private Integer id;
    private Integer transaccionId;
    private Integer usuarioEmisorId;
    private Integer usuarioValoradoId;
    private Float rating;
    private String comentario;
    private LocalDate fechaValoracion;
}