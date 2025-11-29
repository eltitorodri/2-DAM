package com.example.swapy.services;

import com.example.swapy.dto.CrearTransaccionDTO;
import com.example.swapy.models.Chat;
import com.example.swapy.models.Prendas;
import com.example.swapy.models.Transacciones;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.ChatRepository;
import com.example.swapy.repositories.PrendasRepository;
import com.example.swapy.repositories.TransaccionesRepository;
import com.example.swapy.repositories.UsuariosRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TransaccionesServices {

    private final TransaccionesRepository transaccionesRepository;
    private final UsuariosRepository usuariosRepository;
    private final PrendasRepository prendasRepository;
    private final ChatRepository chatRepository;

    @Transactional
    public Transacciones crearSolicitud(CrearTransaccionDTO dto) {

        if (!"Prestamo".equalsIgnoreCase(dto.getTipoTransaccion())) {
            throw new RuntimeException("ERROR: Solo soportamos Prestamo con la estructura actual.");
        }

        Usuarios solicitante = usuariosRepository.findById(dto.getUsuarioSolicitanteId())
                .orElseThrow(() -> new RuntimeException("Usuario solicitante no encontrado"));

        Prendas prendaDeseada = prendasRepository.findById(dto.getPrendaSolicitanteId())
                .orElseThrow(() -> new RuntimeException("Prenda solicitada no encontrada"));

        Usuarios propietario = prendaDeseada.getUsuario();

        Transacciones nuevaTransaccion = new Transacciones();
        nuevaTransaccion.setSolicitante(solicitante);
        nuevaTransaccion.setPropietario(propietario);
        nuevaTransaccion.setTipoTransaccion("Prestamo");
        nuevaTransaccion.setEstado("Pendiente");
        nuevaTransaccion.setFechaInicio(dto.getFechaInicio());

        Transacciones transaccionGuardada = transaccionesRepository.save(nuevaTransaccion);

        Chat chat = new Chat();
        chat.setTransaccion(transaccionGuardada);
        chat.setPrenda(prendaDeseada);

        chatRepository.save(chat);

        return transaccionGuardada;
    }

    @Transactional
    public Transacciones actualizarEstado(Integer transaccionId, String nuevoEstado) {
        Transacciones transaccion = transaccionesRepository.findById(transaccionId)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        if (!"Aceptada".equals(nuevoEstado) && !"Rechazada".equals(nuevoEstado) && !"Finalizada".equals(nuevoEstado)) {
            throw new RuntimeException("Estado inválido. Debe ser Aceptada, Rechazada o Finalizada.");
        }

        transaccion.setEstado(nuevoEstado);


        if ("Finalizada".equals(nuevoEstado)) {
            transaccion.setFechaFinReal(LocalDate.now());
        }

        return transaccionesRepository.save(transaccion);
    }
}