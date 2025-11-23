package com.example.swapy.Services;

import com.example.swapy.repositories.MensajeRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;


}
