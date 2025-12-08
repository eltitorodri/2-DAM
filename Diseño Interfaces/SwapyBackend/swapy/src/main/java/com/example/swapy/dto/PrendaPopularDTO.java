package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrendaPopularDTO {

    @NotBlank(message = "Debes de indicar el nombre de la prenda")
    private String nombrePrenda;

    private Long numeroIntercambios;

}
