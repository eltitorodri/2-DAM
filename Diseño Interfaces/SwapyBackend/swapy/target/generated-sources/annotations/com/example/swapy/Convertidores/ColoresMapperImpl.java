package com.example.swapy.Convertidores;

import com.example.swapy.dto.ColoresDTO;
import com.example.swapy.models.Colores;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-21T16:20:30+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class ColoresMapperImpl implements ColoresMapper {

    @Override
    public ColoresDTO convertirAModel(Colores entity) {
        if ( entity == null ) {
            return null;
        }

        ColoresDTO coloresDTO = new ColoresDTO();

        coloresDTO.setId( entity.getId() );
        coloresDTO.setNombreColor( entity.getNombreColor() );

        return coloresDTO;
    }

    @Override
    public Colores convertirAEntity(ColoresDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Colores colores = new Colores();

        colores.setId( dto.getId() );
        colores.setNombreColor( dto.getNombreColor() );

        return colores;
    }
}
