package com.example.swapy.Services;


import com.example.swapy.models.Notificaciones;
import com.example.swapy.models.Usuarios;
import com.example.swapy.repositories.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    public List<Notificaciones> findAll(){
        return notificacionesRepository.findAll();
    }

    public Notificaciones findById(Integer id){
        return notificacionesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna notificacion con este id:" +id ));
    }

    public Notificaciones save(Notificaciones notificaciones){
        return notificacionesRepository.save(notificaciones);
    }

    public void deleteById(Integer id){
        Notificaciones notificaciones = this.findById(id);
        notificacionesRepository.delete(notificaciones);
    }

    public List<Notificaciones> findByUsuario(Usuarios usuario){
        return notificacionesRepository.findByUsuario(usuario);
    }

    public List<Notificaciones> findByFechaCreacion(Date fechaCreacion){
        return notificacionesRepository.findByFechaCreacion(fechaCreacion);
    }

}
