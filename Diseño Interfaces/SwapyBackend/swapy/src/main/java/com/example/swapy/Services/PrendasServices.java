package com.example.swapy.services;

import com.example.swapy.Convertidores.PrendasMapper;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.repositories.PrendasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrendasServices {

    @Autowired
    private PrendasRepository prendasRepository;

    @Autowired
    private PrendasMapper prendasMapper;

    public void crearPrendas(PrendasDTO dto) {
        prendasRepository.save(prendasMapper.convertirAEntity(dto));
    }
}
