package org.example.springfreseria.repositorios;

import org.example.springfreseria.modelos.Finca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IFincaRepository extends JpaRepository<Finca, Integer> {



}
