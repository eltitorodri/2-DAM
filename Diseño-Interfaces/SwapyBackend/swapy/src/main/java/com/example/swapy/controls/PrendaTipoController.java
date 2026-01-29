// Archivo: com.example.swapy.controls.PrendaTipoController.java

package com.example.swapy.controls;

import com.example.swapy.dto.MostrarRelacionesDTO;
import com.example.swapy.services.PrendasTiposService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prendastipos")
@AllArgsConstructor
public class PrendaTipoController {

    private final PrendasTiposService prendaTipoServices;

    // *************************************************************
    // NUEVO MÉTODO: Responde al GET /prendastipos/todas
    // *************************************************************
    @GetMapping("/todas")
    public ResponseEntity<List<MostrarRelacionesDTO>> obtenerTodasPrendasTipo() {
        // Llama al servicio implementado en el Paso 4
        List<MostrarRelacionesDTO> tipos = prendaTipoServices.obtenerTodas();
        // Devuelve la lista con código 200 OK
        return ResponseEntity.ok(tipos);
    }

    // Puedes añadir otros métodos como POST, PUT, DELETE aquí...
}