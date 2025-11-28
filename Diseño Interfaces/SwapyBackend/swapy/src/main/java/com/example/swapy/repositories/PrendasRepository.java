package com.example.swapy.repositories;


import com.example.swapy.dto.PrendasDTO;
import com.example.swapy.models.Imagenes;
import com.example.swapy.models.Marcas;
import com.example.swapy.models.Prendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendasRepository extends JpaRepository<Prendas, Integer> {

    Prendas save(Prendas prendas);

    void delete(Prendas prendas);

    List<Prendas> findByTituloEquals(String titulo);

    List<Prendas> findByEstadoEquals(String estado);

    List<Prendas> findByTipoGuardadoEquals(String tipoGuardado);

}
