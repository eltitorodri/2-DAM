package com.example.swapy.dto;

import com.example.swapy.models.Prendas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenesDTO {

    private int orden;

    private String url_imagen;

}
