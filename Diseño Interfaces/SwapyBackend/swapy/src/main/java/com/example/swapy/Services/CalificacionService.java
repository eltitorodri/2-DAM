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

     public List<Calificacion> findByValoracion(int valoracion){
         return calificacionRepository.findByValoracion(valoracion);
     }

     public List<Calificacion> findByValoracionGreaterThan(int valoracion){
         return calificacionRepository.findByValoracionGreaterThan(valoracion);
     }

     public List<Calificacion> findByValoracionBetween(int valoracion1, int valoracion2){
         return calificacionRepository.findByValoracionBetween(valoracion1, valoracion2);
     }

     public List<Calificacion> findByValoracionMayorOIgual(int valoracion){
         return calificacionRepository.findByValoracionGreaterThanEqual(valoracion);
     }

     public List<Calificacion> findAllOrderByValoracionAsc(){
         return calificacionRepository.findAllByOrderByValoracionAsc();
     }

     public List<Calificacion> findAllOrderByValoracionDesc(){
         return calificacionRepository.findAllByOrderByValoracionDesc();
     }

}
