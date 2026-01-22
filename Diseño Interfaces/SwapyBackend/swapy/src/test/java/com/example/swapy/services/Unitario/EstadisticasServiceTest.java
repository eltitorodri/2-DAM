package com.example.swapy.services.Unitario;


import com.example.swapy.dto.PrendaPopularDTO;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import com.example.swapy.services.CalificacionService;
import com.example.swapy.services.PrendasServices;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EstadisticasServiceTest {

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

    @Autowired
    private PrendasRepository prendasRepository;

    @Autowired
    private TransaccionesRepository transaccionesRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private CalificacionService calificacionService;
    @Autowired
    private PrendasServices prendasServices;

    @Test
    @DisplayName("[TEST UNITARIO] Estadisticas --> Prenda mas popular")
    public void obtenerPrendasPopularTest() {

        Usuarios usuario = new Usuarios();
        usuario.setEmail("test@gmail.com");
        usuario.setNickname("tester");
        usuario.setNombreCompleto("Test User");
        usuario.setPasswordHash("1234");
        usuario = usuariosRepository.save(usuario);

        Categorias cat = new Categorias();
        cat.setNombre("CatTest");
        categoriasRepository.save(cat);

        Marcas mar = new Marcas();
        mar.setNombre("MarTest");
        marcasRepository.save(mar);

        PrendasTipos tipo = new PrendasTipos();
        tipo.setNombre("TipTest");
        prendasTiposRepository.save(tipo);

        Imagenes img = new Imagenes();
        img.setUrl_imagen("url");
        imagenesRepository.save(img);

        Colores col = new Colores();
        col.setNombreColor("ColTest");
        coloresRepository.save(col);
        List<Colores> colores = new ArrayList<>();
        colores.add(col);

        Prendas prendaPopular = new Prendas();
        prendaPopular.setTitulo("Camiseta Popular");
        prendaPopular.setCategorias(cat);
        prendaPopular.setMarcas(mar);
        prendaPopular.setPrendasTipo(tipo);
        prendaPopular.setColores(colores);
        prendaPopular.setImagen(img);
        prendaPopular.setDescripcion("Desc");
        prendaPopular.setEstado("Disponible");
        prendaPopular.setTipoGuardado("Publicado");
        prendaPopular.setUsuario(usuario);
        prendaPopular = prendasRepository.save(prendaPopular);

        Prendas prendaNormal = new Prendas();
        prendaNormal.setTitulo("Pantalon Normal");
        prendaNormal.setCategorias(cat);
        prendaNormal.setMarcas(mar);
        prendaNormal.setPrendasTipo(tipo);
        prendaNormal.setColores(colores);
        prendaNormal.setImagen(img);
        prendaNormal.setDescripcion("Desc");
        prendaNormal.setEstado("Disponible");
        prendaNormal.setTipoGuardado("Publicado");
        prendaNormal.setUsuario(usuario);
        prendaNormal = prendasRepository.save(prendaNormal);

        prendasRepository.flush();

        Transacciones t1 = new Transacciones();
        t1.setTipoTransaccion(TipoTransaccion.Intercambio);
        t1.setEstado(EstadoTransaccion.Finalizada);
        t1.setPropietario(usuario);
        t1.setSolicitante(usuario);
        transaccionesRepository.save(t1);

        Transacciones t2 = new Transacciones();
        t2.setTipoTransaccion(TipoTransaccion.Intercambio);
        t2.setEstado(EstadoTransaccion.Finalizada);
        t2.setPropietario(usuario);
        t2.setSolicitante(usuario);
        transaccionesRepository.save(t2);

        Transacciones t3 = new Transacciones();
        t3.setTipoTransaccion(TipoTransaccion.Intercambio);
        t3.setEstado(EstadoTransaccion.Finalizada);
        t3.setPropietario(usuario);
        t3.setSolicitante(usuario);
        transaccionesRepository.save(t3);

        transaccionesRepository.flush();

        List<PrendaPopularDTO> resultados = prendasServices.obtenerPrendasPopular();

        assertFalse(resultados.isEmpty());
        assertEquals("Camiseta Popular", resultados.get(0).getNombrePrenda());
        assertEquals(2L, resultados.get(0).getNumeroIntercambios());

        if (resultados.size() > 1) {
            assertEquals("Pantalon Normal", resultados.get(1).getNombrePrenda());
            assertEquals(1L, resultados.get(1).getNumeroIntercambios());
        }

    }


}
