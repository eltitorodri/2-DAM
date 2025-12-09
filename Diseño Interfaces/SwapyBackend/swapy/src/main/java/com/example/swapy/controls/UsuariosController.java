package com.example.swapy.controls;

import com.example.swapy.dto.CrearCalificacionDTO;
import com.example.swapy.dto.PrendaPopularDTO;
import com.example.swapy.dto.UsuarioActivosDTO;
import com.example.swapy.models.Calificacion;
import com.example.swapy.services.CalificacionService;
import com.example.swapy.services.UsuariosServicios;
import com.example.swapy.dto.UsuarioDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuariosController {

    private com.example.swapy.services.UsuariosServicios usuariosServicios;

    private final CalificacionService calificacionService;

    @PostMapping("/crearUser")
    public void crearUser(@Valid @RequestBody UsuarioDTO usuariodto){
        usuariosServicios.crearUsuario(usuariodto);
    }

    @GetMapping("/infousuario/{id}")
    public UsuarioDTO consultarPerfilUsuario(@PathVariable Integer id){
        return usuariosServicios.consultarPerfilUsuario(id);
    }

    @PostMapping("/{id}/valoraciones")
    public ResponseEntity<?> registrarCalificacion(@Valid @PathVariable("id") Integer usuarioValoradoId, @RequestBody CrearCalificacionDTO calificacion) {

        try {
            Calificacion nuevaCalificacion = calificacionService.registrarCalificacion(usuarioValoradoId, calificacion);
            return ResponseEntity.status(201).body(nuevaCalificacion);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/intercambiosactivos")
    public ResponseEntity<List<UsuarioActivosDTO>> findUsuarioConMasAceptados(){
        try {
            List<UsuarioActivosDTO> topUsuario = usuariosServicios.findUsuarioConMasAceptados();

            if (topUsuario.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(topUsuario);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
