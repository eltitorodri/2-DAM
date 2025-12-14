package com.example.swapy.services;
import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.dto.*;
import com.example.swapy.models.*;
import com.example.swapy.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
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

    // ---------------------- MÉTODO DE ACTUALIZACIÓN (CORREGIDO) ----------------------

    @Transactional
    public PrendasDTO actualizarPrendas(Integer id, ActualizarPrendasDTO dto) {

        // 1. Obtener la prenda principal, manejando el Optional con orElseThrow
        Prendas prendaExistente = prendasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenda no encontrada con ID: " + id));

        // a) Categoría
        if (dto.getCategorias() != null) {
            prendaExistente.setCategorias(categoriasService.findById(dto.getCategorias()));
        }

        // b) Marca
        if (dto.getMarcas() != null) {
            prendaExistente.setMarcas(marcasServices.findById(dto.getMarcas()));
        }

        // c) Tipo de Prenda
        if (dto.getPrendasTipo() != null) {
            prendaExistente.setPrendasTipo(prendasTiposService.findbyId(dto.getPrendasTipo()));
        }

        // 3. Resolver y actualizar la colección Many-to-Many (Colores)
        if (dto.getColores() != null) {
            if (dto.getColores().isEmpty()) {
                prendaExistente.setColores(Collections.emptyList());
            } else {
                // CORRECCIÓN: Usar el ColoresServices para obtener la lista de entidades
                List<Colores> nuevosColores = coloresServices.findAllByIds(dto.getColores());
                prendaExistente.setColores(nuevosColores);
            }
        } else {
            // Si el campo es null, se mantiene la lista de colores existente,
            // aunque usualmente en una actualización se espera que se envíe una lista vacía para borrar la colección.
        }

        // 4. Actualizar campos simples y estados
        // Se asume que estos campos no son null, o el DTO los maneja (como en tu código anterior)
        prendaExistente.setTitulo(dto.getTitulo());
        prendaExistente.setDescripcion(dto.getDescripcion());
        prendaExistente.setEstado(dto.getEstado());
        prendaExistente.setTipoGuardado(dto.getTipoGuardado());

        // 5. Guardar la entidad gestionada
        Prendas prendaGuardada = prendasRepository.save(prendaExistente);

        return prendasMapper.toDTO(prendaGuardada);
    }

    // ---------------------- MÉTODOS RESTANTES (SIN MODIFICAR) ----------------------

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