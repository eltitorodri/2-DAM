package com.example.swapy.repositories;


import com.example.swapy.models.Prendas_colores;
import com.example.swapy.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendasColoresRepository extends JpaRepository<Prendas_colores, Integer> {

    List<Prendas_colores> findByPrenda_Id(Integer prendas_id);

    List<Prendas_colores> findByColor_Id(Integer colores_id);

}
