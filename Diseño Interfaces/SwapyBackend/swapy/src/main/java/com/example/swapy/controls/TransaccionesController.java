package com.example.swapy.controls;

import com.example.swapy.dto.ActualizarEstadoDTO;
import com.example.swapy.dto.CrearTransaccionDTO;
import com.example.swapy.models.Transacciones;
import com.example.swapy.services.TransaccionesServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacciones")
@AllArgsConstructor
public class TransaccionesController {

    private final TransaccionesServices transaccionesServices;

    @PostMapping("/solicitar")
    public ResponseEntity<?> crearTransaccion(@Valid @RequestBody CrearTransaccionDTO transacciones){
        try {
            Transacciones nuevaSolicitud = transaccionesServices.crearSolicitud(transacciones);
            return ResponseEntity.status(201).body(nuevaSolicitud);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("Error al crear la solicitud: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstadoTransaccion(@Valid @PathVariable Integer id, @RequestBody ActualizarEstadoDTO dto) {
        try {
            Transacciones transaccionActualizada = transaccionesServices.actualizarEstado(id, dto.getEstado());
            return ResponseEntity.ok(transaccionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Error al actualizar el estado: " + e.getMessage());
        }
    }

}
