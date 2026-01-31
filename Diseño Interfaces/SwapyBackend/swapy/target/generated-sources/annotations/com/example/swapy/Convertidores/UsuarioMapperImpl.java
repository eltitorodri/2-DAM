package com.example.swapy.Convertidores;

import com.example.swapy.dto.UsuarioDTO;
import com.example.swapy.dto.UsuarioNombreDTO;
import com.example.swapy.models.Usuarios;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-21T16:20:31+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDTO(Usuarios usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setNombreCompleto( usuario.getNombreCompleto() );
        usuarioDTO.setPasswordHash( usuario.getPasswordHash() );
        usuarioDTO.setNickname( usuario.getNickname() );
        usuarioDTO.setEmail( usuario.getEmail() );

        return usuarioDTO;
    }

    @Override
    public UsuarioNombreDTO toNombreDTO(Usuarios usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioNombreDTO usuarioNombreDTO = new UsuarioNombreDTO();

        usuarioNombreDTO.setNombreCompleto( usuario.getNombreCompleto() );

        return usuarioNombreDTO;
    }
}
