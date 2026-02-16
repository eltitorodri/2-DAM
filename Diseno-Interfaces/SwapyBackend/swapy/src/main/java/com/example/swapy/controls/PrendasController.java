package com.example.swapy.controls;


import com.example.swapy.dto.*;
import com.example.swapy.services.PrendasServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prendas")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PrendasController {

    private final PrendasServices prendasServices;

    @PostMapping(value = "/crearPrenda", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PrendasDTO> crearPrendas(
            @RequestParam("file") MultipartFile file,
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("estado") String estado,
            @RequestParam("tipoGuardado") String tipoGuardado,
            @RequestParam("usuario") Integer usuarioId,      // Asumiendo que en tu DTO es Integer
            @RequestParam("categorias") Integer categoriaId,
            @RequestParam("marcas") Integer marcaId,
            @RequestParam("prendasTipo") Integer prendaTipoId,
            @RequestParam(value = "colores", required = false) String coloresString // Recibimos "1,2" como string
    ) {
        // 1. Construir el DTO manualmente con los datos que llegaron
        PublicarPrendas dto = new PublicarPrendas();
        dto.setTitulo(titulo);
        dto.setDescripcion(descripcion);
        dto.setEstado(estado);
        dto.setTipoGuardado(tipoGuardado);
        dto.setUsuario(usuarioId);
        dto.setCategorias(categoriaId);
        dto.setMarcas(marcaId);
        dto.setPrendasTipo(prendaTipoId);

        // Parsear los colores de String "1,2" a List<Integer>
        if (coloresString != null && !coloresString.isEmpty()) {
            List<Integer> listaColores = Arrays.stream(coloresString.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            dto.setColores(listaColores);
        }

        // 2. Llamar al servicio modificado
        PrendasDTO resultado = prendasServices.crearPrendaConImagen(dto, file);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/filtrarestado")
    public List<PrendasDTO> listarPrendasByTipoGuardado(){
        return prendasServices.listarPrendasByTipoGuardado();
    }

    @GetMapping("/filtrarguardado")
    public List<PrendasDTO> listarPrendasByEstado(){
        return prendasServices.listarPrendasByTipoGuardado();
    }

    @GetMapping("/filtradojuntos")
    public List<PrendasDTO> filtrarPrendas(
            @RequestParam String estado,
            @RequestParam String tipoGuardado) {
        return prendasServices.listarPrendasByEstadoAndTipoGuardado(estado, tipoGuardado);
    }


    @PutMapping("/actualizarprenda/{id}")
    public PrendasDTO actualizarPrendas(@Valid @PathVariable Integer id, @RequestBody ActualizarPrendasDTO prendasDTO){
        return prendasServices.actualizarPrendas(id, prendasDTO);
    }

    @GetMapping("/prendaspopulares")
    public ResponseEntity<List<PrendaPopularDTO>> obtenerPrendasPopular(){
        try {
            List<PrendaPopularDTO> topPrendas = prendasServices.obtenerPrendasPopular();

            if (topPrendas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(topPrendas);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/todaslasprendas")
    public List<PrendasDTO> obtenerTodasLasPrendas() {
        return prendasServices.listarTodasLasPrendas();
    }

    @GetMapping("/itemsPrendas")
    public List<MostrarItemPrendaDTO> obtenerItemsPrendas() {
        return prendasServices.obtenerPrendas();
    }

    @DeleteMapping("/borrar/{titulo}")
    public ResponseEntity<Void> eliminarPrendaPorTitulo(@PathVariable String titulo) {
        prendasServices.eliminarPrendaPorTitulo(titulo);
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping("/editar/{titulo}")
    public ResponseEntity<Void> editarPrenda(
            @PathVariable String titulo,
            @RequestBody PrendasDTO dto
    ) {
        prendasServices.editarPrendaPorTitulo(titulo, dto);
        return ResponseEntity.ok().build();
    }*/

}
