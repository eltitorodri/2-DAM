package com.example.swapy.Convertidores;

import com.example.swapy.dto.MarcasDTO;
import com.example.swapy.models.Marcas;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcasMapper {

    MarcasDTO convertirAModel(Marcas entity);

    Marcas convertirAEntity(MarcasDTO dto);

}