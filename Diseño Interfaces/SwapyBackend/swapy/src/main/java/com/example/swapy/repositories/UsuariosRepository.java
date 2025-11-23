package com.example.swapy.repositories;


import com.example.swapy.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    List<Usuarios> findByNombre(String nombre);

    Usuarios findByNombreIgnoreCase(String nombre);

}
