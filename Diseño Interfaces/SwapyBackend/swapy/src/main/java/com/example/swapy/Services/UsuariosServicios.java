package com.example.swapy.Services;


import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServicios {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List<Usuarios> findAll() {
        return usuariosRepository.findAll();
    }

    public Usuarios findById(Integer id) {
        return usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ningún usuario con este id: "+ id));
    }

    public void deleteById(Integer id) {
        usuariosRepository.deleteById(id);
    }

    public Usuarios findByNombreIgnoreCase(String nombre) {
        return usuariosRepository.findByNombreIgnoreCase(nombre);
    }

}
