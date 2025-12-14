// Archivo: com.example.swapy.controls.MarcasController.java (CORREGIDO)

package com.example.swapy.controls;

import com.example.swapy.dto.MarcasDTO;
import com.example.swapy.dto.MostrarRelacionesDTO; // <-- Importamos el nuevo DTO
import com.example.swapy.dto.MostrarRelacionesDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity; // <-- Necesario para ResponseEntity
import org.springframework.web.bind.annotation.GetMapping; // <-- IMPORTANTE
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List; // <-- Necesario para List

@RestController
@RequestMapping("/marcas")
@AllArgsConstructor
public class MarcasController {

    private com.example.swapy.services.MarcasServices marcasServices;

    // *************************************************************
    // NUEVO MÉTODO: Responde al GET /marcas/todas para Angular
    // *************************************************************
    @GetMapping("/todas")
    public ResponseEntity<List<MostrarRelacionesDTO>> obtenerTodasMarcas() {
        // Llama al nuevo método del servicio que debes implementar (Paso 2)
        List<MostrarRelacionesDTO> marcas = marcasServices.obtenerTodas();

        // Devolvemos la lista con un código 200 OK
        return ResponseEntity.ok(marcas);
    }

    // Método existente (PostMapping)
    @PostMapping("/crearMarca")
    public void crearMarca(@Valid @RequestBody MarcasDTO marcasdto){
        marcasServices.crearMarca(marcasdto);
    }


}
