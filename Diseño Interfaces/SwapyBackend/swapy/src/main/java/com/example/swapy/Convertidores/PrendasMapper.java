package com.example.swapy.Convertidores;


import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.models.Prendas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrendasMapper {

    Prendas convertirAEntity(PrendasDTO dto);

    PrendasDTO convertirAModel(Prendas entity);

}
