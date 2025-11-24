package com.example.swapy.Services;


import com.example.swapy.models.Calificacion;
import com.example.swapy.repositories.CalificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

     @Autowired
     CalificacionRepository calificacionRepository;

     public List<Calificacion> findAll(){
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

}
