package com.example.swapy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPerfilDTO {

    private String nickname;
    private String nombreCompleto;
    private String email;
    private Double valoracionPromedio;
    private Integer totalPrendasPublicadas;
    private List<PrendaBasicaDTO> prendasPublicadas;

}
