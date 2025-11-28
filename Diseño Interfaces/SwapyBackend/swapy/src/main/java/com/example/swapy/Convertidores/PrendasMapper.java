package com.example.swapy.Convertidores;

import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.Prendas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrendasMapper {

    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "marcas", ignore = true)
    @Mapping(target = "prendasTipo", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "colores", ignore = true)
    @Mapping(target = "fechaAgregado", ignore = true)
    Prendas toEntity(PublicarPrendas dto);

    Prendas toEntity(PrendasDTO dto);

    PrendasDTO toDTO(Prendas entity);

}