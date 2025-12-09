package com.example.swapy.controls;


import com.example.swapy.dto.ColoresDTO;
import com.example.swapy.dto.PrendasDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colores")
@AllArgsConstructor
public class ColoresController {

    private com.example.swapy.services.ColoresServices coloresServices;

    @PostMapping("/crearColores")
    public void crearColores(@Valid @RequestBody ColoresDTO coloresdto){
        coloresServices.crearColores(coloresdto);
    }

}
