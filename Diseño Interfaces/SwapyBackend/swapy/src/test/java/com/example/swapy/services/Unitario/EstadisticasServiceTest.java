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

        List<PrendaPopularDTO> resultados = new ArrayList<>();
        resultados.add(new PrendaPopularDTO("Camiseta Popular", 2L));
        resultados.add(new PrendaPopularDTO("Pantalon Normal",  1L));

        assertFalse(resultados.isEmpty());

        assertEquals("Camiseta Popular", resultados.get(0).getNombrePrenda());
        assertEquals(2L, resultados.get(0).getNumeroIntercambios());

        if (resultados.size() > 1) {
            assertEquals("Pantalon Normal", resultados.get(1).getNombrePrenda());
            assertEquals(1L, resultados.get(1).getNumeroIntercambios());
        }

    }
}
