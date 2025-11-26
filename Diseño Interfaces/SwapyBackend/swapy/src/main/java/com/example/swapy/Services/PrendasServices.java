package com.example.swapy.services;

import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.dto.ImagenesDTO;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrendasServices {

    @Autowired
    private PrendasRepository prendasRepository;

    @Autowired
    private PrendasMapper prendasMapper;

    public void salvarPrendas(PrendasDTO dto) {
        prendasRepository.save(prendasMapper.convertirAEntity(dto));
    }

    @Autowired
    private CategoriasRepository categoriasRepository;
    @Autowired
    private MarcasRepository marcasRepository;
    @Autowired
    private PrendasTiposRepository prendasTiposRepository;
    @Autowired
    private UsuariosRepository usuariosRepository;
    @Autowired
    private ColoresRepository coloresRepository;
    @Autowired
    private ImagenesRepository imagenesRepository;

    public Prendas crearPrendas(PrendasDTO dto) {

        Integer categoriaId = dto.getCategorias().getId();
        Categorias categorias = categoriasRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoriaId));

        Integer marcasId = dto.getMarcas().getId();
        Marcas marcas = marcasRepository.findById(marcasId)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + marcasId));

        Integer prendasTipoId = dto.getPrendasTipo().getId();
        Prendas prendas = prendasRepository.findById(prendasTipoId)
                .orElseThrow(() -> new  RuntimeException("Prendas no encontrada con ID: " + prendasTipoId));

        Integer usuarioId = dto.getUsuario().getId();
        Usuarios usuario = usuariosRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrada con ID: " + usuarioId));


        List<Colores> coloresList = dto.getColores().stream()
                .map(id -> coloresRepository.findById(id.getId())
                        .orElseThrow(() -> new RuntimeException("Color no encontrado con ID: " + id)))
                .collect(Collectors.toList());

        Prendas prenda = new Prendas();
        prenda.setTitulo(dto.getTitulo());
        prenda.setDescripcion(dto.getDescripcion());
        prenda.setEstado(dto.getEstado());
        prenda.setEstado(dto.getEstado());
        prenda.setTipoGuardado(dto.getTipoGuardado());
        prenda.setFechaAgregado(LocalDate.now());

        prenda.setCategorias(categorias);
        prenda.setMarcas(marcas);
        prenda.setPrendasTipo(prendas);
        prenda.setUsuario(usuario);
        prenda.setColores(coloresList);

        Prendas prendaGuardada = prendasRepository.save(prenda);

        List<Imagenes> imagenes = dto.getImagenes().stream()
                .map(imagenDto -> {
                    Imagenes imagen = new Imagenes();
                    imagen.setUrl_imagen(imagenDto.getUrl_imagen());
                    imagen.setOrden(imagenDto.getOrden());
                    imagen.setPrenda(prendaGuardada);
                    return imagen;
                })
                .collect(Collectors.toList());
        imagenesRepository.saveAll(imagenes);

        return prendaGuardada;

    }

}
