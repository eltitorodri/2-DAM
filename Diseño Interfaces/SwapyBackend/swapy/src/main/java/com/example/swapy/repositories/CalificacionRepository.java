package com.example.swapy.repositories;

import com.example.swapy.models.Calificacion;
import com.example.swapy.models.Transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {

    List<Calificacion> findByRatingGreaterThan(Float rating);

    List<Calificacion> findByRatingBetween(Float rating1, Float rating2);

    List<Calificacion> findByRating(Float rating);

    List<Calificacion> findByRatingGreaterThanEqual(Float rating);

    List<Calificacion> findAllByOrderByRatingAsc();

    List<Calificacion> findAllByOrderByRatingDesc();

    Calificacion save(Calificacion calificacion);

}
