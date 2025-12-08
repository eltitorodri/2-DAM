package com.example.swapy.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
import lombok.Data;

@Data
public class ActualizarEstadoDTO {
<<<<<<< HEAD

    @NotBlank(message = "El estado ha de ser finalizada para poder valorar la transacción.")
    private String estado;
}


=======
    private String estado;
}
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
