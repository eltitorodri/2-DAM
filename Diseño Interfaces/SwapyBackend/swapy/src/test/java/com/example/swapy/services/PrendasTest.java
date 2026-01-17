package com.example.swapy.services;

import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrendasTest {

    @Autowired
    private PrendasServices prendasServicios;

    @Autowired
    private PrendasRepository prendasRepository;
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private CategoriasRepository categoriasRepository;
    @Autowired
    private MarcasRepository marcasRepository;
    @Autowired
    private PrendasTiposRepository prendasTiposRepository;
    @Autowired
    private ImagenesRepository imagenesRepository;
    @Autowired
    private ColoresRepository coloresRepository;

    private Usuarios usuarioGlobal;
    private Categorias categoriaGlobal;
    private Marcas marcaGlobal;
    private PrendasTipos tipoGlobal;

    private Imagenes imagenGlobal;
    private List<Colores> coloresGlobal;

    @BeforeEach
    void cargarDatos() {

        prendasRepository.deleteAll();
        coloresRepository.deleteAll();
        imagenesRepository.deleteAll();
        usuariosRepository.deleteAll();
        categoriasRepository.deleteAll();
        marcasRepository.deleteAll();
        prendasTiposRepository.deleteAll();

        Usuarios usuario = new Usuarios();
        usuario.setNombreCompleto("Usuario Vendedor");
        usuario.setNickname("vendedor");
        usuario.setEmail("vendedor@gmail.com");
        usuario.setPasswordHash("123456");
        usuarioGlobal = usuariosRepository.saveAndFlush(usuario);

        Categorias categoria = new Categorias();
        categoria.setNombre("Ropa Superior");
        categoriaGlobal = categoriasRepository.saveAndFlush(categoria);

        Marcas marca = new Marcas();
        marca.setNombre("Nike");
        marcaGlobal = marcasRepository.saveAndFlush(marca);

        PrendasTipos tipo = new PrendasTipos();
        tipo.setNombre("Deportiva");
        tipoGlobal = prendasTiposRepository.saveAndFlush(tipo);

        Imagenes img = new Imagenes();
        img.setUrl_imagen("http://ejemplo.com/foto.jpg");
        imagenGlobal = imagenesRepository.saveAndFlush(img);

        Colores color = new Colores();
        color.setNombreColor("Rojo");
        color = coloresRepository.saveAndFlush(color);

        coloresGlobal = new ArrayList<>();
        coloresGlobal.add(color);
    }

    @Test
    @DisplayName("Crear Prenda -> Caso Positivo")
    void crearPrendaPositivo() {

        // Given
        PublicarPrendas dto = new PublicarPrendas();

        dto.setTitulo("Prenda 1");
        dto.setDescripcion("Camiseta en buen estado");

        dto.setEstado("Intercambio");
        dto.setTipoGuardado("Pendiente");

        dto.setUsuario(usuarioGlobal.getId());
        dto.setCategorias(categoriaGlobal.getId());
        dto.setMarcas(marcaGlobal.getId());
        dto.setPrendasTipo(tipoGlobal.getId());
        dto.setImagen(imagenGlobal.getId());

        List<Integer> idsColores = new ArrayList<>();
        for (Colores c : coloresGlobal) {
            idsColores.add(c.getId());
        }
        dto.setColores(idsColores);

        // WHEN
        prendasServicios.crearPrenda(dto);

        prendasRepository.flush();

        // THEN
        Prendas guardada = prendasRepository.findAll().stream()
                .filter(p -> p.getTitulo().equals("Prenda 1"))
                .findFirst()
                .orElse(null);

        assertNotNull(guardada, "La prenda debería haberse guardado en BD");
        assertEquals("Intercambio", guardada.getEstado());
        assertEquals(usuarioGlobal.getId(), guardada.getUsuario().getId());

        assertEquals("Nike", guardada.getMarcas().getNombre());
        assertEquals("Rojo", guardada.getColores().get(0).getNombreColor());
    }

    @Test
    @DisplayName("Crear Prenda -> Caso Negativo")
    void crearPrendaNegativo() {

        //Given

        PublicarPrendas dto = new PublicarPrendas();

        dto.setTitulo("");
        dto.setDescripcion("Camiseta en buen estado");

        dto.setEstado("Intercambio");
        dto.setTipoGuardado("Pendiente");

        dto.setUsuario(usuarioGlobal.getId());
        dto.setCategorias(categoriaGlobal.getId());
        dto.setMarcas(marcaGlobal.getId());
        dto.setPrendasTipo(tipoGlobal.getId());
        dto.setImagen(imagenGlobal.getId());

        List<Integer> idsColores = new ArrayList<>();
        for (Colores c : coloresGlobal) {
            idsColores.add(c.getId());
        }
        dto.setColores(idsColores);

        //When y Then

        assertThrows(Exception.class, () -> {
            prendasServicios.crearPrenda(dto);
            prendasRepository.flush();
        });

    }

    @Test
    @DisplayName("Consultar prenda por Estado y TipoGuardado - Caso Positivo")
    void consultarPrendaPositivo() {

        // Given
        PublicarPrendas dto = new PublicarPrendas();

        dto.setTitulo("Prenda 1");
        dto.setDescripcion("Camiseta en buen estado");

        dto.setEstado("Intercambio");
        dto.setTipoGuardado("Pendiente");

        dto.setUsuario(usuarioGlobal.getId());
        dto.setCategorias(categoriaGlobal.getId());
        dto.setMarcas(marcaGlobal.getId());
        dto.setPrendasTipo(tipoGlobal.getId());
        dto.setImagen(imagenGlobal.getId());

        List<Integer> idsColores = new ArrayList<>();
        for (Colores c : coloresGlobal) {
            idsColores.add(c.getId());
        }
        dto.setColores(idsColores);

        prendasServicios.crearPrenda(dto);

        prendasRepository.flush();

        //When

        var resultados = prendasServicios.listarPrendasByEstadoAndTipoGuardado("Intercambio", "Pendiente");

        //Then

        assertNotNull(resultados, "La prenda deberia existir");
        assertFalse(resultados.isEmpty(), "La prenda deberia existir");
        assertEquals("Prenda 1", resultados.get(0).getTitulo(), "El titulo deberia coincidir");
        assertEquals("Intercambio", resultados.get(0).getEstado(), "El estado deberia coincidir");
    }

}