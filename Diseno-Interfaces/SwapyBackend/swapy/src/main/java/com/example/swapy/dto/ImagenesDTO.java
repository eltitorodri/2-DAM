package com.example.swapy.dto;

import com.example.swapy.models.Prendas;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenesDTO {

    private int orden;

    @NotBlank
    private String url_imagen;

}
