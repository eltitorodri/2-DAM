package com.example.swapy.services;


import com.example.swapy.models.Categorias;
import com.example.swapy.repositories.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<Categorias> findAll(){
        return categoriasRepository.findAll();
    }

    public Categorias save(Categorias categorias){
        return categoriasRepository.save(categorias);
    }

    public Categorias findById(int id){
        return categoriasRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado categoria con este id: "+id));
    }

    public void  delete(int id){
        categoriasRepository.deleteById(id);
    }

    public List<Categorias> findByNombreContaining(String nombre){
        return categoriasRepository.findByNombreContaining(nombre);
    }

}
