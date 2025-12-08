package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColoresDTO {

    @NotBlank(message = "Tienes que poner el id de algun color")
    private Integer id;

    @NotBlank(message = "Tienes que indicar el nombre del color")
    private String nombreColor;

}
