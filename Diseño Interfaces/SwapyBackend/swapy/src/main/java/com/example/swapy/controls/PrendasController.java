package com.example.swapy.controls;


import com.example.swapy.dto.*;
import com.example.swapy.services.PrendasServices;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prendas")
@AllArgsConstructor
public class PrendasController {

    private final PrendasServices prendasServices;

    @PostMapping("/crearPrenda")
    public PrendasDTO crearPrendas(@Valid @RequestBody PublicarPrendas prendasdto){
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
