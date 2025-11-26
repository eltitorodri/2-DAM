package com.example.swapy.services;


import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.PrendasRepository;
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
        return usuariosRepository.findByNombreCompletoIgnoreCase(nombre);
    }

    public void crearUsuario(UsuarioDTO dto) {

        Usuarios usuario = new Usuarios();

        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setEmail(dto.getEmail());
        usuario.setNickname(dto.getNickname());
        usuario.setPasswordHash(dto.getPasswordHash());

        usuariosRepository.save(usuario);

    }


}
