package com.example.swapy.repositories;


import com.example.swapy.models.Colores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColoresRepository extends JpaRepository<Colores, Integer> {

    List<Colores> findByColoresContaining(String nombre);

    Colores save(Colores colores);

}
