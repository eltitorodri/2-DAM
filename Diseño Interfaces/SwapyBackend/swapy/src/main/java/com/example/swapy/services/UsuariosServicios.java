package com.example.swapy.services;


import com.example.swapy.dto.UsuarioActivosDTO;
import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.PrendasRepository;
import com.example.swapy.repositories.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public UsuarioDTO  consultarPerfilUsuario(Integer id) {

        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        UsuarioDTO dto = new UsuarioDTO();

        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setEmail(usuario.getEmail());
        dto.setNickname(usuario.getNickname());
        dto.setPasswordHash(usuario.getPasswordHash());
        return dto;

    }

    public List<UsuarioActivosDTO> findUsuarioConMasAceptados() {
        List<Object[]> resultados = usuariosRepository.findUsuarioConMasAceptados();

        return resultados.stream()
                .map(row -> new UsuarioActivosDTO(
                        (String) row[0],
                        ((Number) row [1]).longValue())
                )
                .collect(Collectors.toList());
    }


}
