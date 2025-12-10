package com.example.swapy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColoresDTO {

    private Integer id;

    @NotBlank(message = "Tienes que indicar el nombre del color")
    private String nombreColor;

}
