package com.example.swapy.dto;


<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
=======
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrendaPopularDTO {

<<<<<<< HEAD
    @NotBlank(message = "Debes de indicar el nombre de la prenda")
    private String nombrePrenda;

=======
    private String nombrePrenda;
>>>>>>> 769d9085263c0a816577cacdb6ea8f8fe5ffb6f1
    private Long numeroIntercambios;

}
