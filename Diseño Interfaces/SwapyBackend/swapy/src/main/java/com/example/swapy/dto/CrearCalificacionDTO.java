package com.example.swapy.dto;

<<<<<<< HEAD
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
import lombok.Data;

@Data
public class CrearCalificacionDTO {

<<<<<<< HEAD
    @NotBlank(message = "Tienes que indicar el id del usuario emisor de la calificación")
    private Integer usuarioEmisorId;

    @NotBlank(message = "Has de indicar el id de alguna transacción")
    private Integer transaccionId;

    @Min(value = 0, message = "El rating ha de ser mínimo un 0.")
    @Max(value = 10, message = "El rating ha de ser máximo un 10.")
    private Float rating;

    @NotBlank
=======
    private Integer usuarioEmisorId;
    private Integer transaccionId;
    private Float rating;
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
    private String comentario;

}
