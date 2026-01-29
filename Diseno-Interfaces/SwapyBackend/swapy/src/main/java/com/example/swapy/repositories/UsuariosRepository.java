package com.example.swapy.repositories;


import com.example.swapy.models.Prendas;
import com.example.swapy.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    List<Usuarios> findByNombreCompleto(String nombreCompleto);

    Usuarios findByNombreCompletoIgnoreCase(String nombreCompleto);

    Usuarios findByNickname(String nickname);

    boolean existsByNicknameIgnoreCase(String nickname);

    boolean existsByEmailIgnoreCase(String email);

    @Query(value = "select u.nombre_completo as nombreCompleto, count(t.id) as numeroIntercambios\n" +
            "from usuarios u join transacciones t \n" +
            "on (u.id = t.solicitante_id or u.id = t.propietario_id)\n" +
            "where t.estado = 'Aceptada'\n" +
            "group by u.id, u.nickname \n" +
            "order by numerointercambios desc\n" +
            "limit 1", nativeQuery = true)
    List<Object[]> findUsuarioConMasAceptados();

}
