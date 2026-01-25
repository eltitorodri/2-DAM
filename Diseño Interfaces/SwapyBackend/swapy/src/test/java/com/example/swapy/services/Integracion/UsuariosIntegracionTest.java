package com.example.swapy.services.Integracion;


import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.UsuariosRepository;
import com.example.swapy.services.UsuariosServicios;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Transactional
public class UsuariosIntegracionTest {


    @Autowired
    private UsuariosServicios servicio;

    @MockitoBean
    private UsuariosRepository repository;

    @Test
    @DisplayName("[TEST INTEGRACION 1] Crear Usuario --> Caso Positivo")
    public void crearUsuarioPositivoIntegracion() {


        //GIVEN
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail("rbernalgomez@safareyes.es");
        dto.setNickname("eltitorodri");
        dto.setNombreCompleto("Rodrigo Bernal Gómez");
        dto.setPasswordHash("1234");


        Usuarios usuarioSimulado = new Usuarios();
        usuarioSimulado.setEmail("rbernalgomez@safareyes.es");
        usuarioSimulado.setNickname("eltitorodri");
        usuarioSimulado.setNombreCompleto("Rodrigo Bernal Gómez");

        Mockito.when(repository.findByNombreCompletoIgnoreCase("Rodrigo Bernal Gómez")).thenReturn(usuarioSimulado);

        //WHEN

        servicio.crearUsuario(dto);

        //THEN

        Mockito.verify(repository).save(any(Usuarios.class));

        Usuarios resultado = repository.findByNombreCompletoIgnoreCase("Rodrigo Bernal Gómez");


        assertNotNull(resultado, "El usuario no se ha creado correctamente en la base de datos");
        assertEquals("rbernalgomez@safareyes.es", resultado.getEmail(), "El email del usuario no coincide");
        assertEquals("eltitorodri", resultado.getNickname(), "El nickname del usuario no coincide");

    }

    @Test
    @DisplayName("[TEST INTEGRACION 2] Obtener Usuario por ID --> Caso Positivo")
    public void obtenerUsuarioPorID() {


        Usuarios usuario = new Usuarios();
        usuario.setPasswordHash("1234");
        usuario.setNickname("eltitorodri");
        usuario.setEmail("rbernalgomez@safareyes.es");
        usuario.setNombreCompleto("Rodrigo Bernal");

        Mockito.when(repository.findById(1)).thenReturn(Optional.of(usuario));

        UsuarioDTO dto = servicio.consultarPerfilUsuario(1);

        Mockito.verify(repository).findById(1);

        assertNotNull(dto, "El usuario buscado no deberia ser nulo");
        assertEquals(dto.getEmail(), "rbernalgomez@safareyes.es", "El email del usuario no coincide");

    }

}
