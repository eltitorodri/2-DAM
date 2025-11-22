package com.example.swapy.repositories;

import com.example.swapy.models.Marcas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcasRepository extends JpaRepository<Marcas, Integer> {

    Marcas findByMarca(String marca);

    List<Marcas> findByMarcaContaining(String nombre);

}
