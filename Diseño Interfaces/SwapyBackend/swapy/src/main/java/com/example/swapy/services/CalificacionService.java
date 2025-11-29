package com.example.swapy.services;


import com.example.swapy.dto.CrearCalificacionDTO;
import com.example.swapy.models.Calificacion;
import com.example.swapy.models.Transacciones;
import com.example.swapy.repositories.CalificacionRepository;
import com.example.swapy.repositories.TransaccionesRepository;
import com.example.swapy.repositories.UsuariosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalificacionService {

     @Autowired
     CalificacionRepository calificacionRepository;


     public List<Calificacion> findAllSegundo(){
         return calificacionRepository.findAll();
     }

     public Calificacion findById(Integer id){
         return calificacionRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("No se ha encontrado ninguna calificacion con este id: "+id));
     }

     public Calificacion save(Calificacion calificacion){
         return calificacionRepository.save(calificacion);
     }

     public void delete(Integer id){
         calificacionRepository.deleteById(id);
     }

     public List<Calificacion> findByValoracion(Float rating){
         return calificacionRepository.findByRating(rating);
     }

     public List<Calificacion> findByValoracionGreaterThan(Float rating){
         return calificacionRepository.findByRatingGreaterThan(rating);
     }

     public List<Calificacion> findByValoracionBetween(Float rating1, Float rating2){
         return calificacionRepository.findByRatingBetween(rating1, rating2);
     }

     public List<Calificacion> findByValoracionMayorOIgual(Float rating){
         return calificacionRepository.findByRatingGreaterThanEqual(rating);
     }

     public List<Calificacion> findAllOrderByValoracionAsc(){
         return calificacionRepository.findAllByOrderByRatingAsc();
     }

     public List<Calificacion> findAllOrderByValoracionDesc(){
         return calificacionRepository.findAllByOrderByRatingDesc();
     }

     private final UsuariosRepository usuariosRepository;
     private final TransaccionesRepository transaccionesRepository;

    @Transactional
    public Calificacion registrarCalificacion(Integer usuarioValoradoId, CrearCalificacionDTO dto) {

        Transacciones transaccion = transaccionesRepository.findById(dto.getTransaccionId())
                .orElseThrow(() -> new RuntimeException("No se encontro transaccion"));

        if (!"Finalizada".equals(transaccion.getEstado())) {
            throw new RuntimeException("Solo se pueden valorar las transacciones que estan finalizadas");
        }

        Integer solicitanteId = transaccion.getSolicitante().getId();
        Integer propietarioId = transaccion.getPropietario().getId();
        Integer emisorId = dto.getUsuarioEmisorId();

        if (!((emisorId.equals(solicitanteId) || emisorId.equals(propietarioId)) &&
                (usuarioValoradoId.equals(solicitanteId) || usuarioValoradoId.equals(propietarioId)) ||
                emisorId.equals(usuarioValoradoId))) {

            throw new RuntimeException("El emisor y el receptor deben ser participantes distintos en la transaccion");

        }

        Calificacion calificacion = new Calificacion();
        calificacion.setTransaccion(transaccion);
        calificacion.setRating(dto.getRating());
        calificacion.setRating(dto.getRating());
        calificacion.setComentario(dto.getComentario());
        calificacion.setFechaValoracion(LocalDate.now());

        return calificacionRepository.save(calificacion);

    }


}
