package com.example.swapy.services.Integracion;

import com.example.swapy.Exceptions.ElementoNoEncontradoException;
import com.example.swapy.dto.CrearTransaccionDTO;
import com.example.swapy.models.EstadoTransaccion;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.*;
import com.example.swapy.services.TransaccionesServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class IntercambioIntegracionTest {

    @Autowired
    private TransaccionesServices transaccionesServices;

    @MockitoBean
    private TransaccionesRepository transaccionesRepository;

    @MockitoBean
    private PrendasRepository prendasRepository;

    @MockitoBean
    private UsuariosRepository usuariosRepository;

    @MockitoBean private CategoriasRepository categoriasRepository;
    @MockitoBean private MarcasRepository marcasRepository;
    @MockitoBean private PrendasTiposRepository prendasTiposRepository;
    @MockitoBean private ColoresRepository coloresRepository;
    @MockitoBean private ImagenesRepository imagenesRepository;

    @Test
    @DisplayName("[TEST INTEGRACION 6] Crear Intercambio --> Caso Negativo")
    public void intercambioNegativo() {

        Integer idUsuario = 1;
        Integer idPrendaInexistente = 999;

        Usuarios u = new Usuarios();
        u.setId(idUsuario);
        u.setNombreCompleto("Usuario De Testeo");
        u.setEmail("bananini@gmail.com");

        Mockito.when(usuariosRepository.findById(idUsuario)).thenReturn(Optional.of(u));
        Mockito.when(prendasRepository.findById(idPrendaInexistente)).thenReturn(Optional.empty());

        CrearTransaccionDTO dto = new CrearTransaccionDTO();
        dto.setTipoTransaccion("Intercambio");
        dto.setPrendaSolicitanteId(idPrendaInexistente);
        dto.setUsuarioSolicitanteId(idUsuario);
        dto.setFechaFin(null);
        dto.setFechaInicio(null);

        assertThrows(RuntimeException.class, () -> {
            transaccionesServices.crearSolicitud(dto);
        });

        Mockito.verify(transaccionesRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("[TEST INTEGRACION 7] Actualizar estado --> Caso Negativo")
    public void actualizarEstadoNegativo() {


        Integer idUsuario = 1;
        Integer idTransaccionInexistente = 999;

        Usuarios u = new Usuarios();
        u.setId(idUsuario);
        u.setNombreCompleto("Usuario De Testeo");

        Mockito.when(usuariosRepository.findById(idUsuario)).thenReturn(Optional.of(u));

        Mockito.when(transaccionesRepository.findById(idTransaccionInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            transaccionesServices.actualizarEstado(idTransaccionInexistente, EstadoTransaccion.Aceptada, idUsuario);
        });
        Mockito.verify(transaccionesRepository, Mockito.never()).save(Mockito.any());


    }

}