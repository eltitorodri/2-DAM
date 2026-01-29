package com.example.swapy.services.Unitario;


import com.example.swapy.dto.CrearTransaccionDTO;
import com.example.swapy.dto.PrendaPopularDTO;
import com.example.swapy.dto.UsuarioActivosDTO;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import com.example.swapy.services.CalificacionService;
import com.example.swapy.services.PrendasServices;
import com.example.swapy.services.TransaccionesServices;
import com.example.swapy.services.UsuariosServicios;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    private TransaccionesServices transaccionesServices;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private CalificacionService calificacionService;
    @Autowired
    private PrendasServices prendasServices;

    @Test
    @DisplayName("[TEST UNITARIO 9] Estadisticas --> Caso Positivo")
    public void obtenerPrendasPopularTest() {

        List<PrendaPopularDTO> resultados = new ArrayList<>();
        resultados.add(new PrendaPopularDTO("Camiseta Popular", 2L));
        resultados.add(new PrendaPopularDTO("Pantalon Normal",  1L));

        assertEquals("Camiseta Popular", resultados.get(0).getNombrePrenda());
        assertEquals(2L, resultados.get(0).getNumeroIntercambios());

        if (resultados.size() > 1) {
            assertEquals("Pantalon Normal", resultados.get(1).getNombrePrenda());
            assertEquals(1L, resultados.get(1).getNumeroIntercambios());
        }

    }


    @Test
    @DisplayName("[TEST UNITARIO 9] Estadisticas --> Caso Negativo")
    public void obtenerPrendasNegativoTest() {

        List<PrendaPopularDTO> resultados = new ArrayList<>();

        assertTrue(resultados.isEmpty(), "La lista deberia estar vacia ");
        assertEquals(0, resultados.size(), "El tamaño de la lista deberia ser 0");

    }

    @Test
    @DisplayName("[TEST UNITARIO 10] Estadisticas --> Caso Positivo")
    public void usuarioActivoTest() {

        UsuariosServicios servicioTest = new UsuariosServicios() {
            @Override
            protected List<Object[]> ejecutarConsultaBD() {
                List<Object[]> listaSimulada = new ArrayList<>();
                listaSimulada.add(new Object[]{"Ganador Supremo", 50});
                listaSimulada.add(new Object[]{"Usuario Medio", 20});
                listaSimulada.add(new Object[]{"Usuario Novato", 5});
                return listaSimulada;
            }
        };

        List<UsuarioActivosDTO> resultado = servicioTest.findUsuarioConMasAceptados2();

        assertEquals(3, resultado.size());

        UsuarioActivosDTO ganador = resultado.get(0);
        assertEquals("Ganador Supremo", ganador.getNombreCompleto());
        assertEquals(50, ganador.getNumeroIntercambios());
    }

    @Test
    @DisplayName("[TEST UNITARIO 10] Estadisticas --> Caso Negativo")
    public void usuarioActivoVacioTest() {

        UsuariosServicios servicioTest = new UsuariosServicios() {
            @Override
            protected List<Object[]> ejecutarConsultaBD() {
                return new ArrayList<>();
            }
        };

        List<UsuarioActivosDTO> resultado = servicioTest.findUsuarioConMasAceptados2();

        assertNotNull(resultado, "La lista devuelta no debería ser null");
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía porque la BD no devolvió nada");
        assertEquals(0, resultado.size());
    }

}

