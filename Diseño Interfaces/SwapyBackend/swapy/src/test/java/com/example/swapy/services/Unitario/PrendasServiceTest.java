package com.example.swapy.services.Unitario;

import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.*;
import com.example.swapy.repositories.CategoriasRepository;
import com.example.swapy.repositories.PrendasRepository;
import com.example.swapy.services.CategoriasService;
import com.example.swapy.repositories.*;
import com.example.swapy.services.PrendasServices;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

        Prendas guardada = repository.findByTituloUnitario(dto.getTitulo());

        assertNotNull(guardada, "La prenda deberia no ser nula");
        assertEquals("Prenda de Homero Tomillo", guardada.getTitulo(), "El titulo deberia coincidir");

    }

}
