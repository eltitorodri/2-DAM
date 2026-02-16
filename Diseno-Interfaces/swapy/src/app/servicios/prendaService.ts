import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { PrendasItem } from '../se-modelos/PrendasItem';
import { environment } from '../../environments/environment'; // Ruta ajustada

@Injectable({
  providedIn: 'root'
})
export class PrendaService {

  private http = inject(HttpClient);
  // Esta variable tomará el valor de localhost en tu PC y de Render en la nube
  private apiUrl = environment.apiUrl; 

  obtenerPrendas(): Observable<PrendasItem[]> {
    return this.http.get<PrendasItem[]>(`${this.apiUrl}/prendas/itemsPrendas`);
  }

  eliminarPrenda(titulo: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/prendas/borrar/${encodeURIComponent(titulo)}`);
  }

  editarPrenda(id: number | string, prendaActualizada: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/prendas/actualizarprenda/${id}`, prendaActualizada);
  }

  actualizarPrenda(prenda: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/prendas/actualizarprenda/${prenda.id}`, prenda); 
  }

  // En prenda.service.ts
  crearPrenda(datos: FormData): Observable<any> {
      return this.http.post(`${this.apiUrl}/prendas/crearPrenda`, datos);
  }
}