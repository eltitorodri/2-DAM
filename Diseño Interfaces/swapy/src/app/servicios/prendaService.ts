import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { PrendasItem } from '../se-modelos/PrendasItem';

@Injectable({
  providedIn: 'root'
})
export class PrendaService {

  private http = inject(HttpClient);

  /**
   * consultar todos +
   * 
   * Crear
   * 
   * Editar
   * 
   * Eliminar  +
   *
   */
  
  obtenerPrendas(): Observable<PrendasItem[]> {

    return this.http.get<PrendasItem[]>("http://localhost:8080/prendas/itemsPrendas")

  }

  eliminarPrenda(titulo: string): Observable<any> {
  return this.http.delete(`http://localhost:8080/prendas/borrar/${encodeURIComponent(titulo)}`);
  }

  editarPrenda(titulo: string, prendaActualizada: any): Observable<any> {
    return this.http.put(`http://localhost:8080/prendas/editar/${titulo}`, prendaActualizada);
  }

}
