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
public class ColoresDTO {

<<<<<<< HEAD
    @NotBlank(message = "Tienes que poner el id de algun color")
    private Integer id;

    @NotBlank(message = "Tienes que indicar el nombre del color")
=======
    private Integer id;
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
    private String nombreColor;

}
