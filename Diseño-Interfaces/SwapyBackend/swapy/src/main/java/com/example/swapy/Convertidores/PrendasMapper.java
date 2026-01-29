package com.example.swapy.Convertidores;

import com.example.swapy.dto.MostrarItemPrendaDTO;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.Prendas;
import com.example.swapy.models.Usuarios;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PrendasMapper {

    @Mapping(target = "categorias", ignore = true)
    @Mapping(target = "marcas", ignore = true)
    @Mapping(target = "prendasTipo", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "colores", ignore = true)
    @Mapping(target = "fechaAgregado", ignore = true)
    @Mapping(target = "imagen", ignore = true)
    Prendas toEntity(PublicarPrendas dto);

    Prendas toEntity(PrendasDTO dto);

    PrendasDTO toDTO(Prendas entity);

    @Mapping(source = "usuario", target = "usuario", qualifiedByName = "mapUsuarioANombre")
    @Mapping(source = "marcas.nombre", target = "nombreMarca")
    @Mapping(source = "imagen.url_imagen", target = "imagenUrl")
    MostrarItemPrendaDTO toMostrarItemPrendaDTO(Prendas prenda);

    @Named("mapUsuarioANombre")
    default String mapUsuarioANombre(Usuarios usuario) {
        return usuario != null ? usuario.getNombreCompleto() : null;
    }

}