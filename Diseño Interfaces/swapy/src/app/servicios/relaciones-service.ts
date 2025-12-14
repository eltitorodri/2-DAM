// Archivo: src/app/servicios/relaciones.service.ts

import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface Relacion {
    id: number;
    nombre: string;
}

@Injectable({
  providedIn: 'root'
})
export class RelacionesService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080'; 

  obtenerCategorias(): Observable<Relacion[]> {
    return this.http.get<Relacion[]>(`${this.apiUrl}/categorias/todas`); 
  }

  obtenerMarcas(): Observable<Relacion[]> {
    return this.http.get<Relacion[]>(`${this.apiUrl}/marcas/todas`);
  }

  obtenerTiposPrenda(): Observable<Relacion[]> {
    return this.http.get<Relacion[]>(`${this.apiUrl}/prendastipos/todas`);
  }
}