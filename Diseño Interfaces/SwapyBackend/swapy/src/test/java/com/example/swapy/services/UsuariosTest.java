package com.example.swapy.services; // 1. Corregido el paquete

import com.example.swapy.Exceptions.ElementoExistenteException;
import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.UsuariosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

// 2. IMPORT CORRECTO DE JUNIT 5
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuariosTest {

    @Autowired
    private UsuariosServicios service;

    @Autowired
    private UsuariosRepository repository;

    @BeforeEach
    void cargarDatos() {
        repository.deleteAll();

        Usuarios usuario = new Usuarios();
        usuario.setNombreCompleto("Usuario De Testeo");
        usuario.setNickname("testeo");
        usuario.setEmail("testeo@gmail.com");
        usuario.setPasswordHash("123456");

        repository.saveAndFlush(usuario);
    }

    @Test
    @DisplayName("Crear Usuario -> Caso Positivo")
    public void crearUsuarioTest() {
        // Given
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombreCompleto("Nuevo Usuario"); 
        usuarioDTO.setNickname("nuevo123");
        usuarioDTO.setEmail("nuevo@gmail.com");
        usuarioDTO.setPasswordHash("123456");

        // Then
        service.crearUsuario(usuarioDTO);

        // When
        Usuarios guardado = repository.findByNombreCompletoIgnoreCase("Nuevo Usuario");

        assertNotNull(guardado, "El usuario no fue guardado en la base de datos");
        assertEquals("nuevo123", guardado.getNickname(), "El nickname no coincide");
    }

    @Test
    @DisplayName("Crear Usuario -> Caso Negativo (Email Duplicado) ")
    public void crearUsuarioNegativoTest() {

        //Given

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombreCompleto("Intento Fallido");
        usuarioDTO.setNickname("intento123");
        usuarioDTO.setEmail("testeo@gmail.com");
        usuarioDTO.setPasswordHash("123456");

        //Then y When

        assertThrows(ElementoExistenteException.class, () -> {
            service.crearUsuario(usuarioDTO);
        }, "Deberia haber fallado porque el email ya esta registrado" );
    }

    private Integer usuarioIdGenerado;

    @BeforeEach
    void cargarDatosDos() {
        repository.deleteAll();

        Usuarios usuario = new Usuarios();
        usuario.setNombreCompleto("Usuario De Testeo");
        usuario.setNickname("testeo");
        usuario.setEmail("testeo@gmail.com");
        usuario.setPasswordHash("123456");

    }

}