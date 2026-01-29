package com.example.swapy.services.Integracion;


import com.example.swapy.dto.CrearCalificacionDTO;
import com.example.swapy.models.EstadoTransaccion;
import com.example.swapy.models.TipoTransaccion;
import com.example.swapy.models.Transacciones;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.*;
import com.example.swapy.services.CalificacionService;
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
public class ValoracionesIntegracionTest {


    @Autowired
    private CalificacionService calificacionService;

    @MockitoBean
    private CalificacionRepository calificacionesRepository;

    @MockitoBean
    private UsuariosRepository usuariosRepository;

    @MockitoBean
    private TransaccionesRepository transaccionesRepository;

    @MockitoBean private PrendasRepository prendasRepository;
    @MockitoBean private CategoriasRepository categoriasRepository;
    @MockitoBean private MarcasRepository marcasRepository;
    @MockitoBean private PrendasTiposRepository prendasTiposRepository;
    @MockitoBean private ColoresRepository coloresRepository;
    @MockitoBean private ImagenesRepository imagenesRepository;

    @Test
    @DisplayName("[TEST INTEGRACION 8] Crear una valoracion --> Caso Negativo")
    public void crearValoracionNegativoIntegracion() {

        Integer idEmisor = 1;
        Integer idReceptor = 2;
        Integer idTransaccion = 10;

        Usuarios usuarioEmisor = new Usuarios();
        usuarioEmisor.setId(idEmisor);
        usuarioEmisor.setEmail("emisor@gmail.com");
        usuarioEmisor.setNickname("emisor");

        Usuarios usuarioReceptor = new Usuarios();
        usuarioReceptor.setId(idReceptor);
        usuarioReceptor.setEmail("receptor@gmail.com");
        usuarioReceptor.setNickname("receptor");

        Transacciones transaccion = new Transacciones();
        transaccion.setId(idTransaccion);
        transaccion.setEstado(EstadoTransaccion.Pendiente);
        transaccion.setPropietario(usuarioReceptor);
        transaccion.setSolicitante(usuarioEmisor);
        transaccion.setTipoTransaccion(TipoTransaccion.Intercambio);

        Mockito.when(usuariosRepository.findById(idEmisor)).thenReturn(Optional.of(usuarioEmisor));
        Mockito.when(usuariosRepository.findById(idReceptor)).thenReturn(Optional.of(usuarioReceptor));
        Mockito.when(transaccionesRepository.findById(idTransaccion)).thenReturn(Optional.of(transaccion));

        CrearCalificacionDTO dto = new CrearCalificacionDTO();
        dto.setComentario("Este comentario no deberia guardarse");
        dto.setRating((float) 8.0);
        dto.setUsuarioEmisorId(idEmisor);
        dto.setTransaccionId(idTransaccion);

        assertThrows(RuntimeException.class, () -> {
            calificacionService.registrarCalificacion(idReceptor, dto);
        });

        Mockito.verify(calificacionesRepository, Mockito.never()).save(Mockito.any());
    }
}
