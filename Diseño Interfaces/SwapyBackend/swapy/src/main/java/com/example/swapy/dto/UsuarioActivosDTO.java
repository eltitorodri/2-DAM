package com.example.swapy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioActivosDTO {

    private String nombreCompleto;
    private Long numeroIntercambios;

}
