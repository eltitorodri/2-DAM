package com.example.swapy.services;


import com.example.swapy.Convertidores.ColoresMapper;
import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.dto.ColoresDTO;
import com.example.swapy.dto.PrendasDTO;
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
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun color con este id: "+ id));
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
        coloresRepository.save(coloresMapper.convertirAEntity(dto));
    }

}
