package com.example.swapy.services.Unitario;

import com.example.swapy.Exceptions.ElementoNoEncontradoException;
import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.UsuariosRepository;
import com.example.swapy.services.UsuariosServicios;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioServiceTest {

    @Autowired
    private UsuariosServicios service;

    @Autowired
    private UsuariosRepository repository;

    @BeforeAll
    void cargarDatos() {

        Usuarios u = new Usuarios();
        u.setEmail("jose@safareyes.es");
        u.setNombreCompleto("Jose Fuentes Laborda");
        u.setNickname("joselaborda");
        u.setPasswordHash("1234");

        Usuarios u2 = new Usuarios();
        u2.setEmail("ruben@safareyes.es");
        u2.setNombreCompleto("Ruben Romero Aponte");
        u2.setNickname("rubenRomero");
        u2.setPasswordHash("1234");

        repository.save(u);
        repository.save(u2);
        repository.flush();

    }

    @Test
    @DisplayName("[TEST UNITARIO 1] Crear usuario --> Caso Positivo")
    public void crearUsuarioPositivo() {

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("rbernalgomez@safareyes.es");
        usuarioDTO.setNickname("eltitorodri");
        usuarioDTO.setNombreCompleto("Rodrigo Bernal Gómez");
        usuarioDTO.setPasswordHash("1234");

        service.crearUsuario(usuarioDTO);

        Usuarios resultado = repository.findByNombreCompletoIgnoreCase("Rodrigo Bernal Gómez");

        assertNotNull(resultado, "El objeto no deberia de ser nulo");
        assertNotNull(resultado.getEmail(), "El email no deberia de ser nulo");
        assertEquals("eltitorodri", resultado.getNickname(), "El nickname no coincide");

    }

    @Test
    @DisplayName("[TEST UNITARIO 1] Crear usuario --> Caso Negativo")
    public void crearUsuarioNegativo() {


        UsuarioDTO usuarioDuplicado = new UsuarioDTO();
        usuarioDuplicado.setEmail("jose@safareyes.es");
        usuarioDuplicado.setNickname("TheRedBoy");
        usuarioDuplicado.setNombreCompleto("Jose Fuentes Loborda");
        usuarioDuplicado.setPasswordHash("12345");

        assertThrows(Exception.class, () -> {
            service.crearUsuario(usuarioDuplicado);
        }, "Deberia de haber saltado un error por usuario con email duplicado");

    }

    @Test
    @DisplayName("[TEST UNITARIO 2] Buscar por ID --> Caso Positivo")
    public void consultarUsuarioPositivo() {


        UsuarioDTO dto = service.consultarPerfilUsuario(1);

        assertNotNull(dto, "El usuario que se ha intentado buscar deberia existir");
        assertEquals(dto.getEmail(), "jose@safareyes.es", "El email del usuario buscado no coincide");


    }

    @Test
    @DisplayName("[TEST UNITARIO 2] Buscar por ID --> Caso Negativo")
    public void consultarUsuarioNegativo() {

        assertThrows(ElementoNoEncontradoException.class, () -> service.consultarPerfilUsuario(9999), "Deberia lanzar excepcion por ID no encontrado");

    }

}