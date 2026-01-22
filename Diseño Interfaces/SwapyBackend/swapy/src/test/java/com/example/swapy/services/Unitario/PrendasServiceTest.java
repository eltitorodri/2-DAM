package com.example.swapy.services.Unitario;

import com.example.swapy.dto.ActualizarPrendasDTO;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.*;
import com.example.swapy.repositories.CategoriasRepository;
import com.example.swapy.repositories.PrendasRepository;
import com.example.swapy.services.CategoriasService;
import com.example.swapy.repositories.*;
import com.example.swapy.services.PrendasServices;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
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

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrendasServiceTest {

    @Autowired
    private PrendasServices service;

    @Autowired
    private PrendasRepository repository;

    @Autowired private CategoriasRepository categoriasRepository;
    @Autowired private MarcasRepository marcasRepository;
    @Autowired private PrendasTiposRepository prendasTiposRepository;
    @Autowired private UsuariosRepository usuariosRepository;
    @Autowired private ColoresRepository coloresRepository;
    @Autowired private ImagenesRepository imagenesRepository;

    @Autowired private EntityManager entityManager;

    @BeforeAll
    void cargarDatos() {

        Categorias categoria = new Categorias();
        categoria.setNombre("Joyas");
        categoriasRepository.save(categoria);

        Marcas marca = new Marcas();
        marca.setNombre("Adidas");
        marcasRepository.save(marca);

        PrendasTipos prendasTipos = new PrendasTipos();
        prendasTipos.setNombre("Traje");
        prendasTiposRepository.save(prendasTipos);

        Usuarios usuario = new Usuarios();
        usuario.setEmail("jose@safareyes.es");
        usuario.setNombreCompleto("Jose Fuentes Laborda");
        usuario.setNickname("joselaborda");
        usuario.setPasswordHash("1234");
        usuariosRepository.save(usuario);

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

        repository.save(prenda);
        repository.flush();

    }

    @Test
    @DisplayName("[TEST UNITARIO] Crear Prenda --> Caso Positivo")
    public void crearPrendaPositivo() {

        PublicarPrendas dto = new PublicarPrendas();

        Categorias categoria = new Categorias();
        categoria.setNombre("Joyas");
        categoria = categoriasRepository.save(categoria);

        Marcas marca = new Marcas();
        marca.setNombre("Adidas");
        marca = marcasRepository.save(marca);

        PrendasTipos prendasTipos = new PrendasTipos();
        prendasTipos.setNombre("Traje");
        prendasTipos = prendasTiposRepository.save(prendasTipos);

        Usuarios usuario = new Usuarios();
        usuario.setEmail("ruben@safareyes.es");
        usuario.setNombreCompleto("Ruben Romero Aponte");
        usuario.setNickname("rromero");
        usuario.setPasswordHash("12345");
        usuario = usuariosRepository.save(usuario);

        Colores color = new Colores();
        color.setNombreColor("Verde");
        color = coloresRepository.save(color);

        Colores color2 = new Colores();
        color2.setNombreColor("Amarillo");
        color2 = coloresRepository.save(color2);

        List<Integer> colores = new ArrayList<>();
        colores.add(color.getId());
        colores.add(color2.getId());

        Imagenes imagen = new Imagenes();
        imagen.setUrl_imagen("http://ejemplo.com/foto1.jpg");
        imagen.setOrden(2);
        imagen = imagenesRepository.save(imagen);

        dto.setTitulo("Prenda de Homero Tomillo");
        dto.setCategorias(categoria.getId());
        dto.setMarcas(marca.getId());
        dto.setPrendasTipo(prendasTipos.getId());
        dto.setDescripcion("Camiseta de traje");
        dto.setEstado("Pendiente");
        dto.setTipoGuardado("Pendiente");
        dto.setUsuario(usuario.getId());
        dto.setColores(colores);
        dto.setImagen(imagen.getId());

        service.crearPrenda(dto);

        repository.flush();

        Prendas guardada = repository.findByTituloUnitario(dto.getTitulo());

        assertNotNull(guardada, "La prenda deberia no ser nula");
        assertEquals("Prenda de Homero Tomillo", guardada.getTitulo(), "El titulo deberia coincidir");

    }

    @Test
    @DisplayName("[TEST UNITARIO] Crear Prenda --> Caso Negativo")
    public void crearPrendaNegativo() {


        Categorias categoria = new Categorias();
        categoria.setNombre("Joyas");
        categoriasRepository.save(categoria);

        Marcas marca = new Marcas();
        marca.setNombre("Adidas");
        marcasRepository.save(marca);

        PrendasTipos prendasTipos = new PrendasTipos();
        prendasTipos.setNombre("Traje");
        prendasTiposRepository.save(prendasTipos);

        Usuarios usuario = new Usuarios();
        usuario.setEmail("jose@safareyes.es");
        usuario.setNombreCompleto("Jose Fuentes Laborda");
        usuario.setNickname("joselaborda");
        usuario.setPasswordHash("1234");
        usuariosRepository.save(usuario);

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

        PublicarPrendas prenda = new PublicarPrendas();
        prenda.setTitulo("Prenda de Joshep Fontains");
        prenda.setCategorias(categoria.getId());
        prenda.setMarcas(marca.getId());
        prenda.setPrendasTipo(prendasTipos.getId());
        prenda.setDescripcion("Camiseta deportiva");
        prenda.setEstado("Pendiente");
        prenda.setTipoGuardado("Pendiente");
        prenda.setUsuario(usuario.getId());
        prenda.setColores(colores.stream().map(Colores::getId).toList());
        prenda.setImagen(imagen.getId());

        assertThrows(Exception.class, () -> {
                service.crearPrenda(prenda);
                repository.flush();
            }, "Deberia de haber saltado un error por titulo duplicado");
    }

    @Test
    @DisplayName("[TEST UNITARIO] Listar Prendas por filtros --> Caso Positivo")
    public void listarPrendasFiltrosPositivo() {

        Categorias categoria = new Categorias();
        categoria.setNombre("Joyas");
        categoriasRepository.save(categoria);

        Marcas marca = new Marcas();
        marca.setNombre("Adidas");
        marcasRepository.save(marca);

        PrendasTipos prendasTipos = new PrendasTipos();
        prendasTipos.setNombre("Traje");
        prendasTiposRepository.save(prendasTipos);

        Usuarios usuario = new Usuarios();
        usuario.setEmail("jose@safareyes.es");
        usuario.setNombreCompleto("Jose Fuentes Laborda");
        usuario.setNickname("joselaborda");
        usuario.setPasswordHash("1234");
        usuariosRepository.save(usuario);

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

        PrendasDTO prenda = new PrendasDTO();
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

        List<PrendasDTO> dtos = new ArrayList<>();
        dtos.add(prenda);

        PrendasDTO resultado = service.listarPrendasByEstadoAndTipoGuardado(dtos.get(0).getEstado(), dtos.get(0).getTipoGuardado()).get(0);

        assertNotNull(resultado, "El objeto no deberia de ser nulo");
        assertEquals("Pendiente", resultado.getEstado(),  "El estado deberia coincidir");
        assertEquals("Pendiente", resultado.getTipoGuardado(),  "El tipo de guardado deberia coincidir");

    }

    @Test
    @DisplayName("[TEST UNITARIO] Listar Prendas por filtros --> Caso Negativo")
    public void listarPrendasFiltrosNegativo() {

        List<PrendasDTO> dto = service.listarPrendasByEstadoAndTipoGuardado("Vendido", "Archivado");

        assertNotNull(dto, "El objeto no deberia de ser nulo");

        assertTrue(dto.isEmpty(), "El objeto deberia estar vacio");
        assertEquals(0, dto.size(), "El objeto deberia estar vacio");

    }

    @Test
    @DisplayName("[TEST UNITARIO] Editar Prenda --> Caso Positivo")
    public void editarPrendaPositivo() {


        Prendas prendaExistente = repository.findByTituloUnitario("Prenda de Joshep Fontains");
        assertNotNull(prendaExistente, "La prenda deberia existir");

        Integer id = prendaExistente.getId();

        ActualizarPrendasDTO dto = new ActualizarPrendasDTO();
        dto.setTitulo("Prenda de Rodrigo Bernal");
        dto.setDescripcion("Camiseta deportiva");
        dto.setEstado("Intercambio");
        dto.setTipoGuardado("Pendiente");
        dto.setCategorias(prendaExistente.getCategorias().getId());
        dto.setPrendasTipo(prendaExistente.getPrendasTipo().getId());
        dto.setMarcas(prendaExistente.getMarcas().getId());
        dto.setColores(prendaExistente.getColores().stream().map(Colores::getId).toList());
        dto.setUsuario(prendaExistente.getUsuario().getId());
        dto.setImagen(prendaExistente.getImagen().getId());

        service.actualizarPrendas(id, dto);


        repository.flush();
        entityManager.clear();

        Prendas prendaActualizada = repository.findById(id).orElse(null);

        assertNotNull(prendaActualizada);
        assertEquals("Prenda de Rodrigo Bernal", prendaActualizada.getTitulo());
        assertEquals("Intercambio", prendaActualizada.getEstado(), "El estado de la prenda deberia ser [PENDIENTE]");

    }

    @Test
    @DisplayName("[TEST UNITARIO] Editar Prenda --> Caso Negativo")
    public void editarPrendaNegativo() {

        Integer idFalso = 999;

        ActualizarPrendasDTO dto = new ActualizarPrendasDTO();
        dto.setTitulo("No importa");

        assertThrows(Exception.class, () -> {
            service.actualizarPrendas(idFalso, dto);

        }, "Deberia fallar al no encontrar el ID");

    }

}
