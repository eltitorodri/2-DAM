package com.example.swapy.Convertidores;

import com.example.swapy.dto.MarcasDTO;
import com.example.swapy.models.Marcas;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcasMapper {

    List<MarcasDTO> toDTO(List<Marcas> entity);

    List<Marcas> toEntity(List<MarcasDTO> dto);

    MarcasDTO toDTOSingular(Marcas entity);

    Marcas toEntitySingular(MarcasDTO dto);

}