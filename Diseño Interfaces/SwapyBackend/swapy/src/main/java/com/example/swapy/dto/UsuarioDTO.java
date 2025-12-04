package com.example.swapy.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nombreCompleto;
    private String passwordHash;

    @NotBlank
    private String nickname;

    @Email
    private String email;

}
