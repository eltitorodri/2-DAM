package com.example.swapy.repositories;


import com.example.swapy.models.Prendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrendasRepository extends JpaRepository<Prendas, Integer> {

    Prendas save(Prendas prendas);

    void delete(Prendas prendas);

    List<Prendas> findByTituloEquals(String titulo);

    List<Prendas> findByEstadoEquals(String estado);

    List<Prendas> findByTipoGuardadoEquals(String tipoGuardado);

    @Query(value = "SELECT p.titulo AS nombrePrenda, COUNT(c.id) AS numeroIntercambios " +
            "FROM prendas p " +
            "JOIN chat c ON p.id = c.prenda_id " +
            "GROUP BY p.id, p.titulo " +
            "ORDER BY numeroIntercambios DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5PrendasPopulares();

    List<Prendas> findByTitulo(String titulo);

    List<Prendas> findAllByTitulo(String titulo);

    List<Prendas> findByEstadoIgnoreCaseAndTipoGuardadoIgnoreCase(String estado, String tipoGuardado);
}
