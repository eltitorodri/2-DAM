package com.example.swapy.dto;

import com.example.swapy.models.Prendas;
import jakarta.persistence.*;
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
public class ImagenesDTO {

    private int orden;

<<<<<<< HEAD
    @NotBlank
=======
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
    private String url_imagen;

}
