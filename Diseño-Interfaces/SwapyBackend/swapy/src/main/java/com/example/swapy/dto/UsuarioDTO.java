package com.example.swapy.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotBlank(message = "El nombre al completo es obligatorio")
    private String nombreCompleto;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña ha de contener por lo menos 6 caracteres")
    private String passwordHash;

    @NotBlank(message = "Debes de indicar un nickname único")
    private String nickname;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

}
