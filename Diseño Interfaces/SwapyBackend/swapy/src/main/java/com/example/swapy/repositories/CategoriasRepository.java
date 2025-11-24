package com.example.swapy.repositories;

import com.example.swapy.models.Calificacion;
import com.example.swapy.models.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriasRepository extends JpaRepository <Categorias, Integer> {

    List<Categorias> findByNombreContaining(String nombre);

    Categorias save(Categorias categorias);

}
