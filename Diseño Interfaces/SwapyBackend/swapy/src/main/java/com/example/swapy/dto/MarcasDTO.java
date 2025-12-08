package com.example.swapy.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarcasDTO {

    @NotBlank(message = "Debes de indicar el nombre de la marca")
    private String nombre;

}
