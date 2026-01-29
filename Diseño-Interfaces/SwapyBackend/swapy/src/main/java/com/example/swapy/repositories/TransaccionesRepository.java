package com.example.swapy.repositories;


import com.example.swapy.models.Transacciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionesRepository extends JpaRepository<Transacciones, Integer> {
}
