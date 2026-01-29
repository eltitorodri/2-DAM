package com.example.swapy.Convertidores;

import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.dto.UsuarioNombreDTO;
import com.example.swapy.models.Usuarios;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDTO(Usuarios usuario);

    UsuarioNombreDTO toNombreDTO(Usuarios usuario);
}
