package com.example.swapy.repositories;


import com.example.swapy.models.Marcas;
import com.example.swapy.models.Prendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrendasRepository extends JpaRepository<Prendas, Integer> {

    Prendas save(Prendas prendas);

    void delete(Prendas prendas);

    List<Prendas> findByTituloPrenda(String tituloPrenda);

    List<Prendas> findByPrendaEstado(String estadoPrenda);

    List<Prendas> findByMarcasPrendas(Marcas marcaPrendas);

    List<Prendas> findByColores_Id(Integer colorId);

}
