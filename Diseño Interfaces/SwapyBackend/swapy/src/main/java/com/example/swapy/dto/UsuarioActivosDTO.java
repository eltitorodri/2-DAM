package com.example.swapy.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioActivosDTO {

<<<<<<< HEAD

    @NotBlank(message = "Debes de indicar tu nombre completo")
    private String nombreCompleto;

=======
    private String nombreCompleto;
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
    private Long numeroIntercambios;

}
