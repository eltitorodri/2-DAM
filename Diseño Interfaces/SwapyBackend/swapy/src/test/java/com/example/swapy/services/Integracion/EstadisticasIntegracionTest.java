package com.example.swapy.services.Integracion;

import com.example.swapy.services.UsuariosServicios;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.example.swapy.dto.PrendaPopularDTO;
import com.example.swapy.dto.UsuarioActivosDTO;
import com.example.swapy.repositories.*;
import com.example.swapy.services.PrendasServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EstadisticasIntegracionTest {

    @Autowired
    private PrendasServices prendasServices;

    @Autowired
    private UsuariosServicios usuariosServices;

    @MockitoBean
    private PrendasRepository prendasRepository;

    @MockitoBean
    private UsuariosRepository usuariosRepository;

    @MockitoBean private TransaccionesRepository transaccionesRepository;
    @MockitoBean private CategoriasRepository categoriasRepository;
    @MockitoBean private MarcasRepository marcasRepository;
    @MockitoBean private PrendasTiposRepository prendasTiposRepository;
    @MockitoBean private ColoresRepository coloresRepository;
    @MockitoBean private ImagenesRepository imagenesRepository;
    @MockitoBean private CalificacionRepository calificacionesRepository;

    @Test
    @DisplayName("[TEST INTEGRACION 9] Estadisticas Prendas --> Caso Negativo")
    public void obtenerPrendasNegativoTest() {

        Mockito.when(prendasRepository.findTop5PrendasPopulares()).thenReturn(new ArrayList<>());

        List<PrendaPopularDTO> resultados = prendasServices.obtenerPrendasPopular();

        assertNotNull(resultados, "La lista no deberia ser nula");
        assertTrue(resultados.isEmpty(), "La lista deberia estar vacia");
        assertEquals(0, resultados.size());
    }

    @Test
    @DisplayName("[TEST INTEGRACION 10] Estadisticas Usuarios --> Caso Negativo (Lista Vacia)")
    public void usuarioActivoVacioTest() {

        Mockito.when(usuariosServices.findUsuarioConMasAceptados2()).thenReturn(List.of());

        List<UsuarioActivosDTO> resultado = usuariosServices.findUsuarioConMasAceptados2();

        assertNotNull(resultado, "La lista devuelta no debería ser null");
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía porque el Mock devolvió vacío");
        assertEquals(0, resultado.size());
    }

}
