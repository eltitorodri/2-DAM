package com.example.swapy.Convertidores;

import com.example.swapy.dto.MarcasDTO;
import com.example.swapy.dto.NombreMarcaDTO;
import com.example.swapy.models.Marcas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-21T16:20:30+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class MarcasMapperImpl implements MarcasMapper {

    @Override
    public List<MarcasDTO> toDTO(List<Marcas> entity) {
        if ( entity == null ) {
            return null;
        }

        List<MarcasDTO> list = new ArrayList<MarcasDTO>( entity.size() );
        for ( Marcas marcas : entity ) {
            list.add( toDTOSingular( marcas ) );
        }

        return list;
    }

    @Override
    public List<Marcas> toEntity(List<MarcasDTO> dto) {
        if ( dto == null ) {
            return null;
        }

        List<Marcas> list = new ArrayList<Marcas>( dto.size() );
        for ( MarcasDTO marcasDTO : dto ) {
            list.add( toEntitySingular( marcasDTO ) );
        }

        return list;
    }

    @Override
    public MarcasDTO toDTOSingular(Marcas entity) {
        if ( entity == null ) {
            return null;
        }

        MarcasDTO marcasDTO = new MarcasDTO();

        marcasDTO.setNombre( entity.getNombre() );

        return marcasDTO;
    }

    @Override
    public Marcas toEntitySingular(MarcasDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Marcas marcas = new Marcas();

        marcas.setNombre( dto.getNombre() );

        return marcas;
    }

    @Override
    public NombreMarcaDTO toNombreDTO(Marcas marcas) {
        if ( marcas == null ) {
            return null;
        }

        NombreMarcaDTO nombreMarcaDTO = new NombreMarcaDTO();

        nombreMarcaDTO.setNombre( marcas.getNombre() );

        return nombreMarcaDTO;
    }
}
