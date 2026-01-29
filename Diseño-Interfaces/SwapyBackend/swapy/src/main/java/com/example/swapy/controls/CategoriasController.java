// Archivo: com.example.swapy.controls.CategoriasController.java

package com.example.swapy.controls;

import com.example.swapy.dto.MostrarRelacionesDTO;
import com.example.swapy.repositories.CategoriasRepository;
import com.example.swapy.services.CategoriasService;
import com.example.swapy.services.CategoriasService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriasController {

    private final CategoriasService categoriasServices;

    // *************************************************************
    // NUEVO MÉTODO: Responde al GET /categorias/todas
    // *************************************************************
    @GetMapping("/todas")
    public ResponseEntity<List<MostrarRelacionesDTO>> obtenerTodasCategorias() {
        // Llama al servicio implementado en el Paso 2
        List<MostrarRelacionesDTO> categorias = categoriasServices.obtenerTodas();
        // Devuelve la lista con código 200 OK
        return ResponseEntity.ok(categorias);
    }

}
