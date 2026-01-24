package com.example.swapy.services.Unitario;


import com.example.swapy.dto.CrearCalificacionDTO;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import com.example.swapy.services.CalificacionService;
import com.example.swapy.services.TransaccionesServices;
import com.example.swapy.services.UsuariosServicios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase
@SpringBootTest
@Transactional
public class ValoracionesServiceTest {


    @Autowired
    private UsuariosServicios usuariosServicios;

    @Autowired
    private CalificacionRepository calificacionRepository;
    @Autowired
    private CalificacionService calificacionService;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired private CategoriasRepository categoriasRepository;
    @Autowired private MarcasRepository marcasRepository;
    @Autowired private PrendasTiposRepository prendasTiposRepository;
    @Autowired private ColoresRepository coloresRepository;
    @Autowired private ImagenesRepository imagenesRepository;
    @Autowired private PrendasRepository prendasRepository;

    @Autowired private TransaccionesServices  transaccionesServices;
    @Autowired private TransaccionesRepository transaccionesRepository;

    @Test
    @DisplayName("[TEST UNITARIO 8] Crear una valoracion --> Caso Positivo")
    public void crearValoracionPositivo() {

        Usuarios usuarioEmisor = new Usuarios();
        usuarioEmisor.setEmail("emisor@gmail.com");
        usuarioEmisor.setNickname("emisor");
        usuarioEmisor.setNombreCompleto("Usuario Emisor");
        usuarioEmisor.setPasswordHash("123456");
        usuarioEmisor = usuariosRepository.save(usuarioEmisor);

        Usuarios usuarioReceptor = new Usuarios();
        usuarioReceptor.setEmail("receptor@gmail.com");
        usuarioReceptor.setNickname("receptor");
        usuarioReceptor.setNombreCompleto("Usuario Receptor");
        usuarioReceptor.setPasswordHash("123456");
        usuarioReceptor = usuariosRepository.save(usuarioReceptor);

        Categorias cat = new Categorias();
        cat.setNombre("Testing");
        categoriasRepository.save(cat);

        Marcas mar = new Marcas();
        mar.setNombre("Testing");
        marcasRepository.save(mar);

        PrendasTipos tipo = new PrendasTipos();
        tipo.setNombre("Testing");
        prendasTiposRepository.save(tipo);

        Imagenes img = new Imagenes();
        img.setUrl_imagen("http://ejemplo.com/foto.jpg");
        imagenesRepository.save(img);

        Colores col = new Colores();
        col.setNombreColor("Testing");
        coloresRepository.save(col);

        Colores col2 = new Colores();
        col2.setNombreColor("Testing2");
        coloresRepository.save(col2);

        List<Colores> colores = new ArrayList<>();
        colores.add(col);
        colores.add(col2);

        Prendas prenda = new Prendas();
        prenda.setTitulo("Testing");
        prenda.setCategorias(cat);
        prenda.setColores(colores);
        prenda.setImagen(img);
        prenda.setMarcas(mar);
        prenda.setPrendasTipo(tipo);
        prenda.setDescripcion("Testing");
        prenda.setEstado("Pendiente");
        prenda.setTipoGuardado("Pendiente");
        prenda.setUsuario(usuarioReceptor);

        prendasRepository.save(prenda);
        prendasRepository.flush();

        Transacciones transaccion = new Transacciones();
        transaccion.setEstado(EstadoTransaccion.Finalizada);
        transaccion.setFechaFinReal(null);
        transaccion.setFechaInicio(null);
        transaccion.setPropietario(usuarioReceptor);
        transaccion.setSolicitante(usuarioEmisor);
        transaccion.setTipoTransaccion(TipoTransaccion.Intercambio);

        transaccionesRepository.save(transaccion);
        transaccionesRepository.flush();

        CrearCalificacionDTO dto = new CrearCalificacionDTO();
        dto.setComentario("Este es un comentario de prueba");
        dto.setRating((float) 8.0);
        dto.setUsuarioEmisorId(usuarioEmisor.getId());
        dto.setTransaccionId(transaccion.getId());

        calificacionService.registrarCalificacion(usuarioReceptor.getId(), dto);
        calificacionRepository.flush();

        List<Calificacion> resultados = calificacionRepository.findAll();

        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());

        Calificacion calificacion = resultados.get(0);

        assertEquals("Este es un comentario de prueba", calificacion.getComentario());
        assertEquals((float) 8.0, calificacion.getRating());
        assertEquals(usuarioEmisor.getId(), calificacion.getTransaccion().getSolicitante().getId());
        assertEquals(transaccion.getId(), calificacion.getTransaccion().getId());

    }


    @Test
    @DisplayName("[TEST UNITARIO 8] Crear una valoracion --> Caso Negativo")
    public void crearValoracionNegativo() {

        Usuarios usuarioEmisor = new Usuarios();
        usuarioEmisor.setEmail("emisor@gmail.com");
        usuarioEmisor.setNickname("emisor");
        usuarioEmisor.setNombreCompleto("Usuario Emisor");
        usuarioEmisor.setPasswordHash("123456");
        usuarioEmisor = usuariosRepository.save(usuarioEmisor);

        Usuarios usuarioReceptor = new Usuarios();
        usuarioReceptor.setEmail("receptor@gmail.com");
        usuarioReceptor.setNickname("receptor");
        usuarioReceptor.setNombreCompleto("Usuario Receptor");
        usuarioReceptor.setPasswordHash("123456");
        usuarioReceptor = usuariosRepository.save(usuarioReceptor);

        Transacciones transaccion = new Transacciones();
        transaccion.setEstado(EstadoTransaccion.Pendiente);
        transaccion.setFechaFinReal(null);
        transaccion.setFechaInicio(null);
        transaccion.setPropietario(usuarioReceptor);
        transaccion.setSolicitante(usuarioEmisor);
        transaccion.setTipoTransaccion(TipoTransaccion.Intercambio);

        transaccionesRepository.save(transaccion);
        transaccionesRepository.flush();

        CrearCalificacionDTO dto = new CrearCalificacionDTO();
        dto.setComentario("Este comentario no deberia guardarse");
        dto.setRating((float) 8.0);
        dto.setUsuarioEmisorId(usuarioEmisor.getId());
        dto.setTransaccionId(transaccion.getId());

        final Integer idReceptor = usuarioReceptor.getId();

        assertThrows(RuntimeException.class, () -> {
            calificacionService.registrarCalificacion(idReceptor, dto);
        });

    }
}
