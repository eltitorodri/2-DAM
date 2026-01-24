package com.example.swapy.services.Unitario;
import com.example.swapy.dto.ActualizarPrendasDTO;
import com.example.swapy.dto.CrearTransaccionDTO;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import com.example.swapy.services.TransaccionesServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance( TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase
public class IntercambioServiceTest {

    @Autowired
    private TransaccionesServices service;

    @Autowired
    private TransaccionesRepository repository;

    @Autowired private UsuariosRepository usuariosRepository;
    @Autowired private CategoriasRepository categoriasRepository;
    @Autowired private MarcasRepository marcasRepository;
    @Autowired private PrendasTiposRepository prendasTiposRepository;
    @Autowired private ColoresRepository coloresRepository;
    @Autowired private ImagenesRepository imagenesRepository;
    @Autowired private PrendasRepository prendasRepository;


    @BeforeAll
    void cargarDatos() {

        Usuarios usuario = new Usuarios();
        usuario.setEmail("rbernalgomez@safareyes.es");
        usuario.setNickname("eltitorodri");
        usuario.setNombreCompleto("Rodrigo Bernal Gomez");
        usuario.setPasswordHash("1234");

        usuario = usuariosRepository.save(usuario);


        Categorias categoria = new Categorias();
        categoria.setNombre("Joyas");
        categoriasRepository.save(categoria);

        Marcas marca = new Marcas();
        marca.setNombre("Adidas");
        marcasRepository.save(marca);

        PrendasTipos prendasTipos = new PrendasTipos();
        prendasTipos.setNombre("Traje");
        prendasTiposRepository.save(prendasTipos);

        Colores color = new Colores();
        color.setNombreColor("Rojo");
        coloresRepository.save(color);

        Colores color2 = new Colores();
        color2.setNombreColor("Azul");
        coloresRepository.save(color2);

        List<Colores> colores = new ArrayList<>();
        colores.add(color);
        colores.add(color2);

        Imagenes imagen = new Imagenes();
        imagen.setUrl_imagen("http://ejemplo.com/foto.jpg");
        imagen.setOrden(2);
        imagenesRepository.save(imagen);

        Prendas prenda = new Prendas();
        prenda.setTitulo("Prenda de Joshep Fontains");
        prenda.setCategorias(categoria);
        prenda.setMarcas(marca);
        prenda.setPrendasTipo(prendasTipos);
        prenda.setDescripcion("Camiseta deportiva");
        prenda.setEstado("Pendiente");
        prenda.setTipoGuardado("Pendiente");
        prenda.setUsuario(usuario);
        prenda.setColores(colores);
        prenda.setImagen(imagen);

        prenda = prendasRepository.save(prenda);

        Transacciones transaccion = new Transacciones();
        transaccion.setTipoTransaccion(TipoTransaccion.Intercambio);

        transaccion.setSolicitante(usuario);
        transaccion.setPropietario(usuario);

        transaccion.setFechaFinReal(null);
        transaccion.setFechaInicio(null);

        repository.save(transaccion);
        repository.flush();

    }

    @Test
    @DisplayName("[TEST UNITARIO 6] Solicitar Intercambio --> Caso Positivo")
    public void intercambioTest() {


        Usuarios usuario = new Usuarios();
        usuario.setEmail("rbernalgomez@safareyes.es");
        usuario.setNickname("eltitorodri");
        usuario.setNombreCompleto("Rodrigo Bernal Gomez");
        usuario.setPasswordHash("1234");

        usuario = usuariosRepository.save(usuario);


        Categorias categoria = new Categorias();
        categoria.setNombre("Joyas");
        categoriasRepository.save(categoria);

        Marcas marca = new Marcas();
        marca.setNombre("Adidas");
        marcasRepository.save(marca);

        PrendasTipos prendasTipos = new PrendasTipos();
        prendasTipos.setNombre("Traje");
        prendasTiposRepository.save(prendasTipos);

        Colores color = new Colores();
        color.setNombreColor("Rojo");
        coloresRepository.save(color);

        Colores color2 = new Colores();
        color2.setNombreColor("Azul");
        coloresRepository.save(color2);

        List<Colores> colores = new ArrayList<>();
        colores.add(color);
        colores.add(color2);

        Imagenes imagen = new Imagenes();
        imagen.setUrl_imagen("http://ejemplo.com/foto.jpg");
        imagen.setOrden(2);
        imagenesRepository.save(imagen);

        Prendas prenda = new Prendas();
        prenda.setTitulo("Prenda de Rodriguini");
        prenda.setCategorias(categoria);
        prenda.setMarcas(marca);
        prenda.setPrendasTipo(prendasTipos);
        prenda.setDescripcion("Camiseta deportiva");
        prenda.setEstado("Pendiente");
        prenda.setTipoGuardado("Pendiente");
        prenda.setUsuario(usuario);
        prenda.setColores(colores);
        prenda.setImagen(imagen);

        prenda = prendasRepository.save(prenda);

        CrearTransaccionDTO dto = new CrearTransaccionDTO();

        dto.setTipoTransaccion("Intercambio");
        dto.setPrendaSolicitanteId(prenda.getId());
        dto.setUsuarioSolicitanteId(usuario.getId());
        dto.setFechaFin(null);
        dto.setFechaInicio(null);

        service.crearSolicitud(dto);
        repository.flush();

        assertNotNull(dto, "Deberia de haber creado una solicitud de intercambio");
        assertEquals("Intercambio", dto.getTipoTransaccion(), "El tipo de transaccion deberia ser Intercambio");

    }

    @Test
    @DisplayName("[TEST UNITARIO 6] Solicitar Intercambio --> Caso Negativo")
    public void intercambioNegativoTest() {

        Usuarios usuario = new Usuarios();
        usuario.setNombreCompleto("Usuario De Testeo");
        usuario.setNickname("Bananini Cuisini");
        usuario.setPasswordHash("123456");
        usuario.setEmail("rodri@gmail.com");
        usuario = usuariosRepository.save(usuario);

        CrearTransaccionDTO dto = new CrearTransaccionDTO();
        dto.setTipoTransaccion("Intercambio");
        dto.setPrendaSolicitanteId(9999);
        dto.setUsuarioSolicitanteId(usuario.getId());
        dto.setFechaFin(null);
        dto.setFechaInicio(null);

        assertThrows(Exception.class, () -> {
            service.crearSolicitud(dto);
            repository.flush();
        }, "Deberia lanzar excepcion por no encontrar la prenda solicitada");

    }

    @Test
    @DisplayName("[TEST UNITARIO 7] Actualizar estado del intercambio --> Caso Positivo")
    public void actualizarEstadoIntercambioTest() {

        Usuarios u = new Usuarios();
        u.setNombreCompleto("Usuario De Testeo");
        u.setNickname("Bananini Cuisini");
        u.setPasswordHash("123456");
        u.setEmail("rodri@gmail.com");
        u = usuariosRepository.save(u);

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
        prenda.setDescripcion("Testing");

        Transacciones transaccion = new Transacciones();
        transaccion.setEstado(EstadoTransaccion.Pendiente);
        transaccion.setFechaFinReal(null);
        transaccion.setFechaInicio(null);
        transaccion.setPropietario(u);
        transaccion.setSolicitante(u);
        transaccion.setTipoTransaccion(TipoTransaccion.Intercambio);

        transaccion = repository.save(transaccion);
        Integer idTrans = transaccion.getId();

        ActualizarPrendasDTO actPrenda = new ActualizarPrendasDTO();
        actPrenda.setEstado(EstadoTransaccion.Finalizada.toString());

    }



    @Test
    @DisplayName("[TEST UNITARIO 7] Actualizar estado del intercambio --> Caso Negativo")
    public void actualizarEstadoIntercambioTestNegativo() {

            Usuarios u = new Usuarios();
            u.setNombreCompleto("Usuario De Testeo");
            u.setNickname("Bananini Cuisini");
            u.setPasswordHash("123456");
            u.setEmail("rodri@gmail.com");
            u = usuariosRepository.save(u);

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
            prenda.setDescripcion("Testing");

            Transacciones transaccion = new Transacciones();
            transaccion.setEstado(EstadoTransaccion.Pendiente);
            transaccion.setFechaFinReal(null);
            transaccion.setFechaInicio(null);
            transaccion.setPropietario(u);
            transaccion.setSolicitante(u);
            transaccion.setTipoTransaccion(TipoTransaccion.Intercambio);

            transaccion = repository.save(transaccion);
            Integer idTrans = transaccion.getId();

            ActualizarPrendasDTO dto = new ActualizarPrendasDTO();
            dto.setEstado(EstadoTransaccion.Finalizada.toString());

            service.actualizarEstado(u.getId(), EstadoTransaccion.Aceptada, u.getId());
            repository.flush();

            Transacciones resultado = repository.findById(idTrans).get();
            assertEquals(EstadoTransaccion.Aceptada, resultado.getEstado(), "Deberian de ser iguales");

    }



}

