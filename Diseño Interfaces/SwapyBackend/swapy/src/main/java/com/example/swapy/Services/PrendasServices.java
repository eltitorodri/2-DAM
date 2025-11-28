package com.example.swapy.services;
import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.Colores;
import com.example.swapy.models.Prendas;
import com.example.swapy.repositories.*;
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

}
