package com.example.swapy.services;

import com.example.swapy.dto.CrearTransaccionDTO;
import com.example.swapy.dto.TransaccionResponseDTO;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TransaccionesServices {

    private final TransaccionesRepository transaccionesRepository;
    private final UsuariosRepository usuariosRepository;
    private final PrendasRepository prendasRepository;


    @Transactional
    public Transacciones crearSolicitud(CrearTransaccionDTO dto) {

        TipoTransaccion tipo = TipoTransaccion.valueOf(dto.getTipoTransaccion());

        if (tipo != TipoTransaccion.Prestamo && tipo != TipoTransaccion.Intercambio) {
            throw new RuntimeException("ERROR: El tipo de transacción debe ser 'Prestamo' o 'Intercambio'.");
        }

        Usuarios solicitante = usuariosRepository.findById(dto.getUsuarioSolicitanteId())
                .orElseThrow(() -> new RuntimeException("Usuario solicitante no encontrado"));

        Prendas prendaDeseada = prendasRepository.findById(dto.getPrendaSolicitanteId())
                .orElseThrow(() -> new RuntimeException("Prenda solicitada no encontrada"));

        Usuarios propietario = prendaDeseada.getUsuario();

        Transacciones nuevaTransaccion = new Transacciones();
        nuevaTransaccion.setSolicitante(solicitante);
        nuevaTransaccion.setPropietario(propietario);
        nuevaTransaccion.setTipoTransaccion(tipo);
        nuevaTransaccion.setEstado(EstadoTransaccion.Pendiente);
        nuevaTransaccion.setFechaInicio(dto.getFechaInicio());

        return transaccionesRepository.save(nuevaTransaccion);
    }

    @Transactional
    public TransaccionResponseDTO actualizarEstado(Integer transaccionId, EstadoTransaccion nuevoEstado, Integer usuarioActualId) {

        Transacciones transaccion = transaccionesRepository.findById(transaccionId)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada."));

        Integer propietarioId = Integer.valueOf(transaccion.getPropietario().getId());
        Integer solicitanteId = Integer.valueOf(transaccion.getSolicitante().getId());

        if (!propietarioId.equals(usuarioActualId) && !solicitanteId.equals(usuarioActualId)) {
            throw new RuntimeException("Acceso denegado. Solo el propietario o el solicitante pueden actualizar el estado.");
        }

        if (nuevoEstado == EstadoTransaccion.Finalizada && transaccion.getEstado() != EstadoTransaccion.Aceptada) {
            throw new RuntimeException("Solo se puede finalizar una transacción que ya haya sido Aceptada. Estado actual: " + transaccion.getEstado());
        }

        if (!(nuevoEstado == EstadoTransaccion.Finalizada ||
                nuevoEstado == EstadoTransaccion.Aceptada)) {
            throw new RuntimeException("Solo se puede cambiar el estado a ACEPTADA o FINALIZADA.");
        }


        if ((nuevoEstado == EstadoTransaccion.Aceptada || nuevoEstado == EstadoTransaccion.Rechazada) && !propietarioId.equals(usuarioActualId)) {
            throw new RuntimeException("Solo el propietario puede ACEPTAR o RECHAZAR.");
        }


        transaccion.setEstado(nuevoEstado);

        if (nuevoEstado == EstadoTransaccion.Finalizada) {
            transaccion.setFechaFinReal(LocalDate.now());
        }

        Transacciones transaccionActualizada = transaccionesRepository.save(transaccion);

        return mapToResponseDTO(transaccionActualizada);
    }

    private TransaccionResponseDTO mapToResponseDTO(Transacciones transaccion) {
        TransaccionResponseDTO dto = new TransaccionResponseDTO();
        dto.setId(transaccion.getId());

        dto.setSolicitanteId(transaccion.getSolicitante().getId());
        dto.setPropietarioId(transaccion.getPropietario().getId());

        dto.setTipoTransaccion(transaccion.getTipoTransaccion());
        dto.setEstado(transaccion.getEstado());
        dto.setFechaInicio(transaccion.getFechaInicio());
        dto.setFechaFinReal(transaccion.getFechaFinReal());

        return dto;
    }
}