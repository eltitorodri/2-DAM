package com.example.swapy.Services;


import com.example.swapy.models.Marcas;
import com.example.swapy.repositories.MarcasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcasServices {

    @Autowired
    private MarcasRepository marcasRepository;

    public List<Marcas> findAll(){
        return marcasRepository.findAll();
    }

    public Marcas findById(Integer id){
        return marcasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna marca con este id: " +id ));
    }

    public Marcas save(Marcas marcas){
        return marcasRepository.save(marcas);
    }

    public void delete(Marcas marcas){
        marcasRepository.delete(marcas);
    }

    public Marcas findByNombreContaining(String nombre){
        return marcasRepository.findByNombre(nombre);
    }

    public void deleteById(Integer id){
        Marcas marcas = findById(id);
        marcasRepository.delete(marcas);
    }

}
