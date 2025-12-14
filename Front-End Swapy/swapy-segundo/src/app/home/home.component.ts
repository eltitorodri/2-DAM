import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';
import { Router, RouterModule } from '@angular/router'; // Para la navegación
import { PrendaService } from '../services/prenda.service'; // Importación de tu servicio
import { Prenda } from '../models/prenda'; // Importación de tu modelo
import { addIcons } from 'ionicons';
import { add, trash, create, eye } from 'ionicons/icons'; // Iconos para las acciones

@Component({
  selector: 'app-home',
  templateUrl: 'home.component.html',
  styleUrls: ['home.component.scss'],
  standalone: true,
  // IMPORTANTE: Importamos todo lo necesario para Standalone
  imports: [IonicModule, CommonModule, RouterModule], 
})
export class HomeComponent { // Usamos HomeComponent para ser consistentes con 'ng generate component'

  prendas: Prenda[] = [];

  constructor(private prendaService: PrendaService) {
    // Registrar iconos
    addIcons({ add, trash, create, eye });
  }

  // Se ejecuta cada vez que entramos a la pantalla (bueno para recargar la lista)
  ionViewWillEnter() {
    this.cargarPrendas();
  }

  cargarPrendas() {
    this.prendaService.getPrendas().subscribe(
      (data) => {
        this.prendas = data;
        console.log('Prendas cargadas:', this.prendas);
      },
      (error) => {
        console.error('Error al cargar las prendas:', error);
        // Manejo de error si el servidor no está encendido
      }
    );
  }

  eliminarPrenda(event: Event, id: number | undefined) {
    event.stopPropagation(); // Evita que se dispare el evento del <ion-card>
    
    if (!id) return;

    // Aquí podrías agregar un AlertController para confirmar la eliminación
    
    this.prendaService.deletePrenda(id).subscribe(
      () => {
        console.log('Prenda eliminada:', id);
        this.cargarPrendas(); // Recargar la lista después de borrar
      },
      (error) => {
        console.error('Error al eliminar:', error);
      }
    );
  }
}
