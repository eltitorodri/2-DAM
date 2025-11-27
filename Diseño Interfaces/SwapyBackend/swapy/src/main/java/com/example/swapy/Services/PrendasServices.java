package com.example.swapy.services;

import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.Prendas;
import com.example.swapy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrendasServices {

    @Autowired
    private PrendasRepository prendasRepository;

    @Autowired
    private PrendasMapper prendasMapper;

    public void salvarPrendas(PrendasDTO dto) {
        prendasRepository.save(prendasMapper.convertirAEntity(dto));
    }

    public void crearPrendas(PublicarPrendas dto){

        Prendas prendas = new Prendas();

        dto.setTitulo(prendas.getTitulo());

    }

}
