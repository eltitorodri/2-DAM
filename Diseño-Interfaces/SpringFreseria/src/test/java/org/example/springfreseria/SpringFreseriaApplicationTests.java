package org.example.springfreseria;

import org.example.springfreseria.modelos.Finca;
import org.example.springfreseria.repositorios.IFincaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringFreseriaApplicationTests {

    @Autowired
    private IFincaRepository tiendaRepository;
    @Autowired
    private DataSourcePoolMetadataProvider dataSourcePoolMetadataProvider;

    @Test
    void consultarFinca() {

        List<Finca> fincas = tiendaRepository.findAll();

        for  (Finca f : fincas) {
            System.out.println(f.getNombre());
        }

    }

}
