package com.example.swapy.Mappers;


import com.example.swapy.dto.PrendaBasicaDTO;
import com.example.swapy.dto.UsuarioPerfilDTO;
import com.example.swapy.models.Prendas;
import com.example.swapy.models.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    PrendaBasicaDTO toPrendaBasicaDTO(Prendas prenda);

    List<PrendaBasicaDTO> toPrendaBasicaDTO(List<Prendas> prendas);

    @Mapping(target = "reputacionPromedio", ignore = true)
    @Mapping(target = "totalPrendasPublicadas", ignore = true)
    @Mapping(target = "prendasPublicadas", ignore = true)
    UsuarioPerfilDTO toUsuarioPerfilDTO(Usuarios usuario);

}
