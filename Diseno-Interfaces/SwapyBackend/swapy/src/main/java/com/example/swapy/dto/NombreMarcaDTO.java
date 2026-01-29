package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NombreMarcaDTO {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

}
