package com.example.swapy.repositories;


import com.example.swapy.models.Notificaciones;
import com.example.swapy.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Integer> {

    List<Notificaciones> findByUsuario(Usuarios usuario);

    List<Notificaciones> findByFechaCreacion(Date fechaCreacion);

}
