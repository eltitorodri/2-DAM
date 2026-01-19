package com.example.swapy.services.Unitario;

import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.models.Usuarios;
import com.example.swapy.services.UsuariosServicios;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioServiceTest {

    @Autowired
    private UsuariosServicios service;

    @Autowired
    private EntityManager entityManager;

    @BeforeAll
    void cargarDatos() {

        Usuarios u = new Usuarios();
        u.setEmail("jose@safareyes.es");
        u.setNombreCompleto("Jose Fuentes Laborda");
        u.setNickname("joselaborda");
        u.setPasswordHash(1234);

        Usuarios u2 = new Usuarios();
        u2.setEmail("ruben@safareyes.es");
        u2.setNombreCompleto("Ruben Romero Aponte");
        u2.setNickname("rubenRomero");
        u2.setPasswordHash("1234");

        entityManager.persist(u);
        entityManager.persist(u2);
        entityManager.flush();

    }


    @Test
    @DisplayName("[TEST UNITARIO] Crear usuario --> Caso Positivo")
    public void crearUsuarioPositivo() {


        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("rbernalgomez@safareyes.es");
        usuarioDTO.setNickname("eltitorodri");
        usuarioDTO.setNombreCompleto("Rodrigo Bernal Gómez");
        usuarioDTO.setPasswordHash("1234");

        UsuarioDTO resultado = service.crearUsuario(usuarioDTO);

        assertNotNull(resultado, "El objeto no deberia de ser nulo");
        assertNotNull(resultado.getEmail(), "El email no deberia de ser nulo");
        assertEquals("Usuario Creado", resultado.getNickname(), "El nickname no coincide");

    }


}
