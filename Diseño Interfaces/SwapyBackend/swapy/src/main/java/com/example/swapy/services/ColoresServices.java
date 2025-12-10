package com.example.swapy.services;


import com.example.swapy.Convertidores.ColoresMapper;
import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.Exceptions.ElementoExistenteException;
import com.example.swapy.dto.ColoresDTO;
import com.example.swapy.Exceptions.ElementoNoEncontradoException;
import com.example.swapy.models.Colores;
import com.example.swapy.repositories.ColoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColoresServices {

    @Autowired
    private ColoresRepository coloresRepository;

    public List<Colores> findAll(){
        return coloresRepository.findAll();
    }

    public Colores findById(Integer id){
        return coloresRepository.findById(id)
                .orElseThrow(() -> new ElementoNoEncontradoException("No se ha encontrado ningun color con este id: "+ id));
    }

    public List<Colores> findAllByIds(List<Integer> ids) {
        return coloresRepository.findAllById(ids);
    }

    public Colores save(Colores colores){
        return coloresRepository.save(colores);
    }

    public void deleteById(Integer id){
        coloresRepository.deleteById(id);
    }

    public List<Colores> findByNombreContaining(String nombre){
        return coloresRepository.findByNombreColorContaining(nombre);
    }

    @Autowired
    private ColoresMapper coloresMapper;

    public void crearColores(ColoresDTO dto) {

        if (coloresRepository.existsByNombreColorIgnoreCase(dto.getNombreColor())) {
            throw new ElementoExistenteException("nombreColor",
                    "El color con nombre: " + dto.getNombreColor() + " ya existe");
        }

        coloresRepository.save(coloresMapper.convertirAEntity(dto));

    }

}
