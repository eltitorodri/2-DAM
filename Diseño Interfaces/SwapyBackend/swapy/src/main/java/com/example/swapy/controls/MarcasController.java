package com.example.swapy.controls;


import com.example.swapy.dto.MarcasDTO;
import com.example.swapy.dto.PrendasDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marcas")
@AllArgsConstructor
public class MarcasController {

    private com.example.swapy.services.MarcasServices marcasServices;

    @PostMapping("/crearMarca")
    public void crearMarca(@Valid @RequestBody MarcasDTO marcasdto){
        marcasServices.crearMarca(marcasdto);
    }

}
