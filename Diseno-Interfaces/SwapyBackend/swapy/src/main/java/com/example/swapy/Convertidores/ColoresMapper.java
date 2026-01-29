package com.example.swapy.Convertidores;

import com.example.swapy.dto.ColoresDTO;
import com.example.swapy.models.Colores;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ColoresMapper {

    ColoresDTO convertirAModel(Colores entity);

    Colores convertirAEntity(ColoresDTO dto);

}
