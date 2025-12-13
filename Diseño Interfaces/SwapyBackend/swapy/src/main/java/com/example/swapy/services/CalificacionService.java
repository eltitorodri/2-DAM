package com.example.swapy.services;

import com.example.swapy.dto.CalificacionResponseDTO;
import com.example.swapy.dto.CrearCalificacionDTO;
import com.example.swapy.models.Calificacion;
import com.example.swapy.models.EstadoTransaccion;
import com.example.swapy.models.Transacciones;
import com.example.swapy.repositories.CalificacionRepository;
import com.example.swapy.repositories.TransaccionesRepository;
import com.example.swapy.repositories.UsuariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;

    private final UsuariosRepository usuariosRepository;
    private final TransaccionesRepository transaccionesRepository;

    @Transactional
    public CalificacionResponseDTO registrarCalificacion(Integer usuarioValoradoId, CrearCalificacionDTO dto) {

        Transacciones transaccion = transaccionesRepository.findById(dto.getTransaccionId())
                .orElseThrow(() -> new RuntimeException("No se encontro transaccion"));

        if (transaccion.getEstado() != EstadoTransaccion.Finalizada) {
            throw new RuntimeException("Solo se pueden valorar las transacciones que estan finalizadas");
        }

        Integer solicitanteId = transaccion.getSolicitante().getId();
        Integer propietarioId = transaccion.getPropietario().getId();
        Integer emisorId = dto.getUsuarioEmisorId();

        boolean emisorEsParticipante = emisorId.equals(solicitanteId) || emisorId.equals(propietarioId);
        boolean valoradoEsParticipante = usuarioValoradoId.equals(solicitanteId) || usuarioValoradoId.equals(propietarioId);

        if (!emisorEsParticipante || !valoradoEsParticipante) {
            throw new RuntimeException("Solo los participantes de la transacción pueden valorarse.");
        }

        if (emisorId.equals(usuarioValoradoId)) {
            throw new RuntimeException("Un usuario no puede valorarse a sí mismo.");
        }

        boolean casoSolicitanteValora = emisorId.equals(solicitanteId) && usuarioValoradoId.equals(propietarioId);

        boolean casoPropietarioValora = emisorId.equals(propietarioId) && usuarioValoradoId.equals(solicitanteId);

        if (!(casoSolicitanteValora || casoPropietarioValora)) {
            throw new RuntimeException("Error de lógica: El emisor y el receptor deben ser las dos partes distintas de esta transacción.");
        }

        Calificacion calificacion = new Calificacion();
        calificacion.setTransaccion(transaccion);
        calificacion.setRating(dto.getRating());
        calificacion.setComentario(dto.getComentario());
        calificacion.setFechaValoracion(LocalDate.now());

        Calificacion calificacionGuardada = calificacionRepository.save(calificacion);

        // 2. Llamar al mapeador y retornar el DTO
        return mapToResponseDTO(calificacionGuardada);
    }

    private CalificacionResponseDTO mapToResponseDTO(Calificacion calificacion) {
        CalificacionResponseDTO dto = new CalificacionResponseDTO();
        dto.setId(calificacion.getId());
        dto.setRating(calificacion.getRating());
        dto.setComentario(calificacion.getComentario());
        dto.setFechaValoracion(calificacion.getFechaValoracion());

        dto.setTransaccionId(calificacion.getTransaccion().getId());

        return dto;
    }

}