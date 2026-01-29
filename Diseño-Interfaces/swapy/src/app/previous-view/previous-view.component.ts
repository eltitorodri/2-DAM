import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ColorModalComponent } from '../color-modal/color-modal.component';
import { SloganLogoComponent } from "../slogan-logo/slogan-logo.component";
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";

interface Color {
  id: string;
  name: string;
}

@Component({
  selector: 'app-previous-view',
  templateUrl: './previous-view.component.html',
  styleUrls: ['./previous-view.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, ColorModalComponent, SloganLogoComponent, NavegationBarComponent],
})
export class PreviousViewComponent {
  titulo = signal('');
  categoria = signal('');
  marca = signal('');
  descripcion = signal('');

  tallas = ['XS', 'S', 'M', 'L', 'XL', 'XXL'];
  tallaSeleccionada = signal<string | null>(null);

  estados = [
    { label: 'Nuevo', value: 'nuevo' },
    { label: 'Como nuevo', value: 'como_nuevo' },
    { label: 'Usado', value: 'usado' },
    { label: 'Con defectos', value: 'con_defectos' }
  ];
  estadoSeleccionado = signal<string | null>(null);

  colores = signal<Color[]>([
    { id: '1', name: 'Rosa' },
    { id: '2', name: 'Beige' }
  ]);

  imagenes = signal<string[]>([
    'https://via.placeholder.com/80',
    'https://via.placeholder.com/80',
    'https://via.placeholder.com/80',
    'https://via.placeholder.com/80'
  ]);

  mostrarModalColor = signal(false);

  seleccionarTalla(talla: string) {
    this.tallaSeleccionada.set(talla);
  }

  seleccionarEstado(estado: string) {
    this.estadoSeleccionado.set(estado);
  }

  abrirModalColor() {
    this.mostrarModalColor.set(true);
  }

  cerrarModalColor() {
    this.mostrarModalColor.set(false);
  }

  agregarColor(nombreColor: string) {
    const nuevoColor: Color = {
      id: Date.now().toString(),
      name: nombreColor
    };
    this.colores.update(colors => [...colors, nuevoColor]);
    this.cerrarModalColor();
  }

  publicarProducto() {
    const producto = {
      titulo: this.titulo(),
      categoria: this.categoria(),
      talla: this.tallaSeleccionada(),
      estado: this.estadoSeleccionado(),
      marca: this.marca(),
      colores: this.colores(),
      descripcion: this.descripcion()
    };
    console.log('Publicar producto:', producto);
  }
}