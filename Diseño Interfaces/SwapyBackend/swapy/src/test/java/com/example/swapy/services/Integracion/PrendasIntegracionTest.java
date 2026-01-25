package com.example.swapy.services.Integracion;

import com.example.swapy.dto.ActualizarPrendasDTO;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import com.example.swapy.services.PrendasServices;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;

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
    @Autowired
    private PrendasServices prendasServices;

    @Test
    @DisplayName("[TEST INTEGRACION 3] Crear Prenda --> Caso Positivo")
    public void crearPrendaPositivo() {


        Categorias cat = new Categorias();
        cat.setNombre("Camisas");
        cat.setId(1);
        Mockito.when(categoriasRepository.findById(1)).thenReturn(Optional.of(cat));

        Marcas mar = new Marcas();
        mar.setNombre("Adidas");
        mar.setId(1);
        Mockito.when(marcasRepository.findById(1)).thenReturn(Optional.of(mar));

        PrendasTipos pt = new PrendasTipos();
        pt.setNombre("TipoPrenda");
        pt.setId(1);
        Mockito.when(prendasTiposRepository.findById(1)).thenReturn(Optional.of(pt));

        Usuarios usuario = new Usuarios();
        usuario.setId(1);
        usuario.setNickname("usuarioTest");
        usuario.setEmail("usuarioTest");
        usuario.setPasswordHash("12234");
        usuario.setNombreCompleto("usuarioTest");
        Mockito.when(usuariosRepository.findById(1)).thenReturn(Optional.of(usuario));

        Imagenes img = new Imagenes();
        img.setId(1);
        img.setUrl_imagen("http://imagen.com/1");
        Mockito.when(imagenesRepository.findById(1)).thenReturn(Optional.of(img));

        Colores color1 = new Colores();
        color1.setId(1);
        color1.setNombreColor("Rojo");

        Colores color2 = new Colores();
        color2.setId(2);
        color2.setNombreColor("Azul");

        Mockito.when(coloresRepository.findAllById(anyList())).thenReturn(List.of(color1, color2));

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

        Mockito.verify(prendasRepository).save(prendaCaptor.capture());
        Prendas prendaGuardada = prendaCaptor.getValue();

        assertNotNull(prendaGuardada, "La prenda no deberia ser nula");
        assertEquals("Prenda de Homero Tomillo", prendaGuardada.getTitulo(), "El titulo deberia coincidir");
        assertEquals("Pendiente", prendaGuardada.getEstado(), "El estado deberia coincidir");

        assertEquals("Adidas", prendaGuardada.getMarcas().getNombre(), "La marca deberia coincidir");

    }

    @Test
    @DisplayName("[TEST INTEGRACION 4] Listar Prendas por filtros --> Caso Positivo")
    public void listarPrendasFiltrosPositivo() {


        Categorias cat = new Categorias();
        cat.setNombre("Camisas");

        Marcas mar = new Marcas();
        mar.setNombre("Adidas");

        PrendasTipos pt = new PrendasTipos();
        pt.setNombre("Traje");

        Usuarios u = new Usuarios();
        u.setEmail("rbernalgomez@safareyes.es");
        u.setNickname("eltitorodri");

        Imagenes img = new Imagenes();
        img.setUrl_imagen("http://imagen.com/1");

        Prendas prenda = new Prendas();
        prenda.setTitulo("Prenda de Homero Tomillo");
        prenda.setDescripcion("Camiseta de traje");
        prenda.setEstado("Pendiente");
        prenda.setTipoGuardado("Pendiente");

        prenda.setCategorias(cat);
        prenda.setMarcas(mar);
        prenda.setPrendasTipo(pt);
        prenda.setUsuario(u);
        prenda.setImagen(img);


        Mockito.when(prendasRepository.findByEstadoIgnoreCaseAndTipoGuardadoIgnoreCase("Pendiente", "Pendiente"))
                .thenReturn(List.of(prenda));

        List<PrendasDTO> resultadoLista = prendasServices.listarPrendasByEstadoAndTipoGuardado("Pendiente", "Pendiente");

        Mockito.verify(prendasRepository).findByEstadoIgnoreCaseAndTipoGuardadoIgnoreCase("Pendiente", "Pendiente");

        assertNotNull(resultadoLista, "La lista deberia ser nula");
        assertEquals(1, resultadoLista.size(), "Deberia haber una prenda en la lista");

        PrendasDTO dtoResultado = resultadoLista.get(0);

        assertEquals("Pendiente", dtoResultado.getEstado(), "El estado deberia coincidir");
        assertEquals("Pendiente", dtoResultado.getTipoGuardado(), "El tipo de guardado deberia coincidir");

        assertEquals("Adidas", dtoResultado.getMarcas().getNombre(), "La marca deberia coincidir");

    }

    @Test
    @DisplayName("[TEST INTEGRACION 5] Editar Prenda --> Caso Positivo")
    public void editarPrendaFiltroPositivo() {

        Integer idPrenda = 1;

        Categorias cat = new Categorias(); cat.setId(1);
        Marcas mar = new Marcas(); mar.setId(1);
        PrendasTipos tipo = new PrendasTipos(); tipo.setId(1);
        Usuarios user = new Usuarios(); user.setId(1);
        Imagenes img = new Imagenes(); img.setId(1);

        Colores col = new Colores(); col.setId(1); col.setNombreColor("Rojo");

        Prendas prendaVieja = new Prendas();
        prendaVieja.setId(idPrenda);
        prendaVieja.setTitulo("Prenda de Homero Tomillo");
        prendaVieja.setEstado("Pendiente");

        prendaVieja.setCategorias(cat);
        prendaVieja.setMarcas(mar);
        prendaVieja.setPrendasTipo(tipo);
        prendaVieja.setUsuario(user);
        prendaVieja.setImagen(img);
        prendaVieja.setColores(List.of(col));

        Mockito.when(prendasRepository.findById(idPrenda)).thenReturn(Optional.of(prendaVieja));
        Mockito.when(categoriasRepository.findById(1)).thenReturn(Optional.of(cat));
        Mockito.when(marcasRepository.findById(1)).thenReturn(Optional.of(mar));
        Mockito.when(prendasTiposRepository.findById(1)).thenReturn(Optional.of(tipo));
        Mockito.when(usuariosRepository.findById(1)).thenReturn(Optional.of(user));
        Mockito.when(imagenesRepository.findById(1)).thenReturn(Optional.of(img));

        Mockito.when(coloresRepository.findById(1)).thenReturn(Optional.of(col));
        Mockito.when(coloresRepository.findAllById(anyList())).thenReturn(List.of(col));

        ActualizarPrendasDTO dto = new ActualizarPrendasDTO();
        dto.setTitulo("Prenda Actualizada");
        dto.setDescripcion("Descripcion Actualizada");
        dto.setEstado("Intercambio");
        dto.setTipoGuardado("Pendiente");

        dto.setCategorias(1);
        dto.setPrendasTipo(1);
        dto.setMarcas(1);
        dto.setUsuario(1);
        dto.setImagen(1);

        List<Integer> idsColores = new ArrayList<>();
        idsColores.add(1);
        dto.setColores(idsColores);

        servicio.actualizarPrendas(idPrenda, dto);

        Mockito.verify(prendasRepository).save(prendaCaptor.capture());

        Prendas prendaActualizada = prendaCaptor.getValue();

        assertNotNull(prendaActualizada, "La prenda no deberia ser nula");
        assertEquals("Prenda Actualizada", prendaActualizada.getTitulo(), "El titulo deberia coincidir");
        assertEquals("Intercambio", prendaActualizada.getEstado()," El estado deberia coincidir");
        assertEquals(idPrenda, prendaActualizada.getId(), "La prenda deberia coincidir");
    }


}
