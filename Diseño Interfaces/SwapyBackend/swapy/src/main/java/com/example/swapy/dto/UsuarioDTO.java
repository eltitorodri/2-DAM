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

<<<<<<< HEAD
    @NotBlank(message = "Debes de indicar un nickname único ")
=======
    @NotBlank
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
    private String nickname;

    @Email
    private String email;

}
