package com.example.swapy.repositories;


import com.example.swapy.models.Categorias;
import com.example.swapy.models.PrendasTipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendasTiposRepository extends JpaRepository<PrendasTipos, Integer> {

    List<PrendasTipos> findByNombreContaining(String nombre);

    PrendasTipos save(PrendasTipos prendasTipos);

}
