package com.example.swapy.repositories;

import com.example.swapy.models.Imagenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenesRepository extends JpaRepository<Imagenes, Integer> {

    Imagenes save(Imagenes imagenes);
    void delete(Imagenes imagenes);

}
