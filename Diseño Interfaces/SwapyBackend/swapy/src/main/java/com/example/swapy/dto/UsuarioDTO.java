package com.example.swapy.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nombreCompleto;
    private String passwordHash;
    private String nickname;
    private String email;

}
