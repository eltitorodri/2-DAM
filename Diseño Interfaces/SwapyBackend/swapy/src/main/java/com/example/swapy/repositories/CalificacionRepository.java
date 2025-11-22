package com.example.swapy.repositories;

import com.example.swapy.models.Calificacion;
import com.example.swapy.models.Transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {

    List<Calificacion> findByValoracionGreaterThan(int valoracion);

    List<Calificacion> findByValoracionBetween(int valoracion1, int valoracion2);

    List<Calificacion> findByValoracion(int valoracion);

    List<Calificacion> findByValoracionGreaterThanEqual(int valoracion);

    List<Calificacion> findAllByOrderByValoracionAsc();

    List<Calificacion> findAllByOrderByValoracionDesc();

    Calificacion save(Calificacion calificacion);

}
