package com.example.swapy.services.Integracion;

import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import com.example.swapy.services.PrendasServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class PrendasIntegracionTest {

    @Autowired
    private PrendasServices servicio;

    @MockitoBean
    private PrendasRepository prendasRepository;

    @Captor
    private ArgumentCaptor<Prendas> prendaCaptor;

    @MockitoBean
    private UsuariosRepository repository;
    @MockitoBean private CategoriasRepository categoriasRepository;
    @MockitoBean private MarcasRepository marcasRepository;
    @MockitoBean private PrendasTiposRepository prendasTiposRepository;
    @MockitoBean private UsuariosRepository usuariosRepository;
    @MockitoBean private ColoresRepository coloresRepository;
    @MockitoBean private ImagenesRepository imagenesRepository;

    @Test
    @DisplayName("[TEST INTEGRACION 3] Crear Prenda --> Caso Positivo")
    public void crearPrendaPositivo() {


        Categorias cat = new Categorias();
        cat.setNombre("Camisas");
        cat.setId(1);
        when(categoriasRepository.findById(1)).thenReturn(Optional.of(cat));

        Marcas mar = new Marcas();
        mar.setNombre("Adidas");
        mar.setId(1);
        when(marcasRepository.findById(1)).thenReturn(Optional.of(mar));

        PrendasTipos pt = new PrendasTipos();
        pt.setNombre("TipoPrenda");
        pt.setId(1);
        when(prendasTiposRepository.findById(1)).thenReturn(Optional.of(pt));

        Usuarios usuario = new Usuarios();
        usuario.setId(1);
        usuario.setNickname("usuarioTest");
        usuario.setEmail("usuarioTest");
        usuario.setPasswordHash("12234");
        usuario.setNombreCompleto("usuarioTest");
        when(usuariosRepository.findById(1)).thenReturn(Optional.of(usuario));

        Imagenes img = new Imagenes();
        img.setId(1);
        img.setUrl_imagen("http://imagen.com/1");
        when(imagenesRepository.findById(1)).thenReturn(Optional.of(img));

        Colores color1 = new Colores();
        color1.setId(1);
        color1.setNombreColor("Rojo");

        Colores color2 = new Colores();
        color2.setId(2);
        color2.setNombreColor("Azul");

        when(coloresRepository.findAllById(anyList())).thenReturn(List.of(color1, color2));

        PublicarPrendas dto = new PublicarPrendas();
        dto.setTitulo("Prenda de Homero Tomillo");
        dto.setDescripcion("Camiseta de traje");
        dto.setEstado("Pendiente");
        dto.setTipoGuardado("Pendiente");

        dto.setCategorias(1);
        dto.setMarcas(1);
        dto.setPrendasTipo(1);
        dto.setUsuario(1);
        dto.setImagen(1);

        List<Integer> idsColores = new ArrayList<>();
        idsColores.add(1);
        idsColores.add(2);
        dto.setColores(idsColores);

        servicio.crearPrenda(dto);

        verify(prendasRepository).save(prendaCaptor.capture());
        Prendas prendaGuardada = prendaCaptor.getValue();

        assertNotNull(prendaGuardada, "La prenda no deberia ser nula");
        assertEquals("Prenda de Homero Tomillo", prendaGuardada.getTitulo(), "El titulo deberia coincidir");
        assertEquals("Pendiente", prendaGuardada.getEstado(), "El estado deberia coincidir");

        assertEquals("Adidas", prendaGuardada.getMarcas().getNombre(), "La marca deberia coincidir");

    }

    @Test
    @DisplayName("[TEST INTEGRACION 4] Listar Prendas por filtros --> Caso Positivo")
    public void listarPrendasPor

}
