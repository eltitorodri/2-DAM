package com.example.swapy.services;
import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.dto.MostrarItemPrendaDTO;
import com.example.swapy.dto.PrendaPopularDTO;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.Colores;
import com.example.swapy.models.Prendas;
import com.example.swapy.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PrendasServices {

    @Autowired
    private PrendasRepository prendasRepository;

    @Autowired
    private PrendasMapper prendasMapper;
    @Autowired
    private ImagenesService imagenesService;

    public void salvarPrendas(PrendasDTO dto) {
        prendasRepository.save(prendasMapper.toEntity(dto));
    }

    public void crearPrendas(PrendasDTO dto){

        Prendas prendas = new Prendas();

        dto.setTitulo(prendas.getTitulo());

    }

    @Autowired
    private MarcasServices marcasServices;
    @Autowired
    private UsuariosServicios usuariosServicios;
    @Autowired
    private CategoriasService categoriasService;
    @Autowired
    private PrendasTiposService prendasTiposService;
    @Autowired
    private ColoresServices coloresServices;

    public PrendasDTO crearPrenda(PublicarPrendas dto) {

        Prendas prenda = prendasMapper.toEntity(dto);

        prenda.setFechaAgregado(LocalDate.now());

        prenda.setMarcas(marcasServices.findById(dto.getMarcas()));
        prenda.setUsuario(usuariosServicios.findById(dto.getUsuario()));
        prenda.setCategorias(categoriasService.findById(dto.getCategorias()));
        prenda.setPrendasTipo(prendasTiposService.findbyId(dto.getPrendasTipo()));
        prenda.setImagen(imagenesService.findById(dto.getImagen()));

        if(dto.getColores() != null && !dto.getColores().isEmpty()) {
            List<Colores> coloresEncontrados = coloresServices.findAllByIds(dto.getColores());
            prenda.setColores(coloresEncontrados);
        }

        Prendas prendaGuardada = prendasRepository.save(prenda);

        return prendasMapper.toDTO(prendaGuardada);

    }

    public List<PrendasDTO> listarPrendasByEstado() {
        List<Prendas> prendas = prendasRepository.findByEstadoEquals("Prestamo");
        return prendas.stream()
                .map(prendasMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PrendasDTO> listarPrendasByTipoGuardado() {
        List<Prendas> prendas = prendasRepository.findByTipoGuardadoEquals("Guardado");
        return prendas.stream()
                .map(prendasMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PrendasDTO actualizarPrendas(Integer id, PublicarPrendas dto) {
        Prendas prendaExistente = prendasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenda no encontrada con ID: " +id));

        Prendas prendaActualizada = prendasMapper.toEntity(dto);

        prendaActualizada.setId(prendaExistente.getId());
        prendaActualizada.setFechaAgregado(prendaExistente.getFechaAgregado());
        prendaActualizada.setUsuario(prendaExistente.getUsuario());
        prendaActualizada.setCategorias(prendaExistente.getCategorias());
        prendaActualizada.setMarcas(prendaExistente.getMarcas());
        prendaActualizada.setColores(prendaExistente.getColores());
        prendaActualizada.setPrendasTipo(prendaExistente.getPrendasTipo());

        Prendas prendaGuardada = prendasRepository.save(prendaActualizada);

        return prendasMapper.toDTO(prendaGuardada);

    }

    public List<PrendaPopularDTO> obtenerPrendasPopular() {
        List<Object[]> resultados = prendasRepository.findTop5PrendasPopulares();

        return resultados.stream()
                .map(row -> new PrendaPopularDTO(
                        (String) row [0],
                        ((Number) row [1]).longValue())
                )
                .collect(Collectors.toList());

    }

    public List<PrendasDTO> listarTodasLasPrendas() {
        List<Prendas> prendas = prendasRepository.findAll();
        return prendas.stream()
                .map(prendasMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MostrarItemPrendaDTO> obtenerPrendas() {
        return prendasRepository.findAll()
                .stream()
                .map(prendasMapper::toMostrarItemPrendaDTO)
                .toList();
    }

    public void eliminarPrendaPorTitulo(String titulo) {
        List<Prendas> prendas = prendasRepository.findByTitulo(titulo);
        if (!prendas.isEmpty()) {
            prendasRepository.delete(prendas.get(0));
        } else {
            throw new RuntimeException("Prenda no encontrada");
        }
    }

    @Transactional
    public void editarPrendaPorTitulo(String titulo, PrendasDTO dto) {

        List<Prendas> prendas = prendasRepository.findAllByTitulo(titulo);

        if (prendas.isEmpty()) {
            throw new EntityNotFoundException("No se encontró la prenda");
        }

        Prendas prenda = prendas.get(0);

        prenda.setDescripcion(dto.getDescripcion());
        prenda.setEstado(dto.getEstado());
        prenda.setTipoGuardado(dto.getTipoGuardado());

        prenda.setCategorias(dto.getCategorias());
        prenda.setMarcas(dto.getMarcas());
        prenda.setPrendasTipo(dto.getPrendasTipo());
        prenda.setImagen(dto.getImagen());
        prenda.setColores(dto.getColores());

        prendasRepository.save(prenda);
    }


}
