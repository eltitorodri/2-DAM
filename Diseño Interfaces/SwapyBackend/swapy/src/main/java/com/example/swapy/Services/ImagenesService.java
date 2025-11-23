package com.example.swapy.Services;


import com.example.swapy.models.Imagenes;
import com.example.swapy.repositories.ImagenesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenesService {

    @Autowired
    private ImagenesRepository imagenesRepository;

    public List<Imagenes> findAll(){
        return imagenesRepository.findAll();
    }

    public Imagenes findById(Integer id){
        return imagenesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna imagen con este id:"+id));
    }

    public Imagenes save(Imagenes imagenes){
        return imagenesRepository.save(imagenes);
    }

    public void deleteById(Integer id){
        Imagenes imagenes = findById(id);
        imagenesRepository.delete(imagenes);
    }

    public void delete(Imagenes imagenes){
        imagenesRepository.delete(imagenes);
    }

}
