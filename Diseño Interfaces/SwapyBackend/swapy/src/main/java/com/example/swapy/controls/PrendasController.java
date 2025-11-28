package com.example.swapy.controls;


import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.services.PrendasServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prendas")
@AllArgsConstructor
public class PrendasController {

    private final PrendasServices prendasServices;

    @PostMapping("/crearPrenda")
    public PrendasDTO crearPrendas(@RequestBody PublicarPrendas prendasdto){
        return prendasServices.crearPrenda(prendasdto);
    }

    @GetMapping("/filtrarestado")
    public List<PrendasDTO> listarPrendasByTipoGuardado(){
        return prendasServices.listarPrendasByTipoGuardado();
    }

    @GetMapping("/filtrarguardado")
    public List<PrendasDTO> listarPrendasByEstado(){
        return prendasServices.listarPrendasByTipoGuardado();
    }

}
