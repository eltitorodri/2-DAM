package com.example.swapy.Services;

import com.example.swapy.models.PrendasTipos;
import com.example.swapy.repositories.PrendasTiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrendasTiposService {

    @Autowired
    private PrendasTiposRepository prendasTiposRepository;

    public List<PrendasTipos> findByAll(){
        return prendasTiposRepository.findAll();
    }

    public PrendasTipos findbyId(int id){
        return prendasTiposRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningun tipo de prenda con este id: " +id ));
    }

    public List<PrendasTipos> findByPrendasTiposContaining(String nombre){
        return prendasTiposRepository.findByPrendasTiposContaining(nombre);
    }

    public PrendasTipos save(PrendasTipos prendasTipos){
        return prendasTiposRepository.save(prendasTipos);
    }

    public void deleteById(int id){
        PrendasTipos prendasTipos = findbyId(id);
        prendasTiposRepository.delete(prendasTipos);
    }

}
