package com.example.swapy.Convertidores;

import com.example.swapy.dto.MostrarItemPrendaDTO;
import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.dto.PublicarPrendas;
import com.example.swapy.models.Colores;
import com.example.swapy.models.Imagenes;
import com.example.swapy.models.Marcas;
import com.example.swapy.models.Prendas;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-21T16:20:31+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class PrendasMapperImpl implements PrendasMapper {

    @Override
    public Prendas toEntity(PublicarPrendas dto) {
        if ( dto == null ) {
            return null;
        }

        Prendas prendas = new Prendas();

        prendas.setTitulo( dto.getTitulo() );
        prendas.setDescripcion( dto.getDescripcion() );
        prendas.setEstado( dto.getEstado() );
        prendas.setTipoGuardado( dto.getTipoGuardado() );

        return prendas;
    }

    @Override
    public Prendas toEntity(PrendasDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Prendas prendas = new Prendas();

        prendas.setTitulo( dto.getTitulo() );
        prendas.setDescripcion( dto.getDescripcion() );
        prendas.setFechaAgregado( dto.getFechaAgregado() );
        prendas.setEstado( dto.getEstado() );
        prendas.setTipoGuardado( dto.getTipoGuardado() );
        prendas.setCategorias( dto.getCategorias() );
        prendas.setMarcas( dto.getMarcas() );
        prendas.setPrendasTipo( dto.getPrendasTipo() );
        prendas.setUsuario( dto.getUsuario() );
        prendas.setImagen( dto.getImagen() );
        List<Colores> list = dto.getColores();
        if ( list != null ) {
            prendas.setColores( new ArrayList<Colores>( list ) );
        }

        return prendas;
    }

    @Override
    public PrendasDTO toDTO(Prendas entity) {
        if ( entity == null ) {
            return null;
        }

        PrendasDTO prendasDTO = new PrendasDTO();

        prendasDTO.setTitulo( entity.getTitulo() );
        prendasDTO.setDescripcion( entity.getDescripcion() );
        prendasDTO.setFechaAgregado( entity.getFechaAgregado() );
        prendasDTO.setEstado( entity.getEstado() );
        prendasDTO.setTipoGuardado( entity.getTipoGuardado() );
        prendasDTO.setCategorias( entity.getCategorias() );
        prendasDTO.setMarcas( entity.getMarcas() );
        prendasDTO.setPrendasTipo( entity.getPrendasTipo() );
        prendasDTO.setUsuario( entity.getUsuario() );
        prendasDTO.setImagen( entity.getImagen() );
        List<Colores> list = entity.getColores();
        if ( list != null ) {
            prendasDTO.setColores( new ArrayList<Colores>( list ) );
        }

        return prendasDTO;
    }

    @Override
    public MostrarItemPrendaDTO toMostrarItemPrendaDTO(Prendas prenda) {
        if ( prenda == null ) {
            return null;
        }

        MostrarItemPrendaDTO mostrarItemPrendaDTO = new MostrarItemPrendaDTO();

        mostrarItemPrendaDTO.setUsuario( mapUsuarioANombre( prenda.getUsuario() ) );
        mostrarItemPrendaDTO.setNombreMarca( prendaMarcasNombre( prenda ) );
        mostrarItemPrendaDTO.setImagenUrl( prendaImagenUrl_imagen( prenda ) );
        mostrarItemPrendaDTO.setId( prenda.getId() );
        mostrarItemPrendaDTO.setTitulo( prenda.getTitulo() );

        return mostrarItemPrendaDTO;
    }

    private String prendaMarcasNombre(Prendas prendas) {
        Marcas marcas = prendas.getMarcas();
        if ( marcas == null ) {
            return null;
        }
        return marcas.getNombre();
    }

    private String prendaImagenUrl_imagen(Prendas prendas) {
        Imagenes imagen = prendas.getImagen();
        if ( imagen == null ) {
            return null;
        }
        return imagen.getUrl_imagen();
    }
}
