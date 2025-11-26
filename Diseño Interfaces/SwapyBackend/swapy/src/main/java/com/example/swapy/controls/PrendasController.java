package com.example.swapy.controls;


import com.example.swapy.dto.PrendasDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prendas")
@AllArgsConstructor
public class PrendasController {

    private com.example.swapy.services.PrendasServices prendasServices;

    @PostMapping("/crearPrenda")
    public void crearPrendas(@RequestBody PrendasDTO prendasdto){
        prendasServices.crearPrendas(prendasdto);
    }


}
