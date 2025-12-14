package com.example.swapy.services;
import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.Exceptions.ElementoExistenteException;
import com.example.swapy.Exceptions.ElementoNoEncontradoException;
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
import java.util.Optional;
import java.util.Set;
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

        Prendas prendaExistente = prendasRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenda no encontrada con ID: " + id));

        if (dto.getCategorias() != null) {
            Categorias categoria = categoriasService.findById(dto.getCategorias());
            if (categoria == null) {
                throw new ElementoNoEncontradoException("Debe introducir una Categoría válida para la prenda.");
            }
            prendaExistente.setCategorias(categoria);
        }

        if (dto.getMarcas() != null) {
            Marcas marca = marcasServices.findById(dto.getMarcas());
            if (marca == null) {
                throw new ElementoNoEncontradoException("Debe introducir una Marca válida para la prenda.");
            }
            prendaExistente.setMarcas(marca);
        }

        if (dto.getPrendasTipo() != null) {
            PrendasTipos prendasTipo = prendasTiposService.findbyId(dto.getPrendasTipo());
            if (prendasTipo == null) {
                throw new ElementoNoEncontradoException("Debe introducir un Tipo de Prenda válido para la prenda.");
            }
            prendaExistente.setPrendasTipo(prendasTipo);
        }

        if (dto.getColores() != null) {
            if (dto.getColores().isEmpty()) {
                prendaExistente.setColores(Collections.emptyList());
            } else {
                List<Colores> nuevosColores = coloresServices.findAllByIds(dto.getColores());

                if (nuevosColores.size() != dto.getColores().size()) {
                    throw new ElementoNoEncontradoException("'Colores': Uno o más IDs de Color proporcionados no existen.");
                }
                prendaExistente.setColores(nuevosColores);
            }
        }

        if (dto.getEstado() != null) {
            String estadoNormalizado = dto.getEstado().trim().toLowerCase();
            if (!estadoNormalizado.equals("intercambio") && !estadoNormalizado.equals("prestamo")) {
                throw new ElementoExistenteException("estado",
                        "El valor proporcionado para el Estado ('" + dto.getEstado() + "') no es válido. Debe ser 'Intercambio' o 'Préstamo'."
                );
            }
            prendaExistente.setEstado(dto.getEstado());
        }

        if (dto.getTipoGuardado() != null) {
            String tipoGuardadoNormalizado = dto.getTipoGuardado().trim().toLowerCase();
            if (!tipoGuardadoNormalizado.equals("guardado") && !tipoGuardadoNormalizado.equals("pendiente")) {
                throw new ElementoExistenteException("tipoGuardado",
                        "El valor proporcionado para el Tipo de Guardado ('" + dto.getTipoGuardado() + "') no es válido. Debe ser 'Guardado' o 'Pendiente'."
                );
            }
            prendaExistente.setTipoGuardado(dto.getTipoGuardado());
        }

        prendaExistente.setTitulo(dto.getTitulo());
        prendaExistente.setDescripcion(dto.getDescripcion());
        prendaExistente.setEstado(dto.getEstado());
        prendaExistente.setTipoGuardado(dto.getTipoGuardado());

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