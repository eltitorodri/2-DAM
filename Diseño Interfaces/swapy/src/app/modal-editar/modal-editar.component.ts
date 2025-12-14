import { Component, Input, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule, ModalController } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { Prendas } from '../se-modelos/Prendas';
import { PrendasItem } from '../se-modelos/PrendasItem';
import { Colores } from '../se-modelos/Colores';
import { PrendaService } from '../servicios/prendaService';

@Component({
  selector: 'app-modal-editar',
  templateUrl: './modal-editar.component.html',
  styleUrls: ['./modal-editar.component.scss'],
  standalone: true,
  imports: [CommonModule, IonicModule, FormsModule]
})
export class ModalEditarComponent implements OnInit {
  @Input() prenda!: PrendasItem;

  private prendaService = inject(PrendaService);
  private modalCtrl = inject(ModalController);

  prendaEditable!: Prendas;

  // Variables planas para ngModel
  titulo: string = '';
  descripcion: string = '';
  fechaAgregado: string = '';
  estado: string = '';
  tipoGuardado: string = '';
  categoriaNombre: string = '';
  marcaNombre: string = '';
  prendaTipoNombre: string = '';
  usuarioNombre: string = '';
  imagenUrl: string = '';
  coloresString: string = '';

  ngOnInit() {
    if (this.prenda) {
      // Copiamos todo el objeto para no modificar directamente el original
      this.prendaEditable = JSON.parse(JSON.stringify(this.prenda));

      // Inicializamos variables planas para ngModel
      this.titulo = this.prendaEditable.titulo.toString();
      this.descripcion = this.prendaEditable.descripcion.toString();
      this.fechaAgregado = this.prendaEditable.fechaAgregado.toString();
      this.estado = this.prendaEditable.estado.toString();
      this.tipoGuardado = this.prendaEditable.tipoGuardado.toString();
      this.categoriaNombre = this.prendaEditable.categorias?.nombre || '';
      this.marcaNombre = this.prendaEditable.marcas?.nombre || '';
      this.prendaTipoNombre = this.prendaEditable.prendasTipo?.nombre || '';
      this.usuarioNombre = this.prendaEditable.usuario?.nombreCompleto || '';
      this.imagenUrl = this.prendaEditable.imagen?.url_imagen || '';
      this.coloresString = this.prendaEditable.colores?.map(c => c.nombreColor).join(', ') || '';
    }
  }

  cerrarModal(actualizado = false) {
    this.modalCtrl.dismiss({ actualizado });
  }

  guardarCambios() {
    // Fecha
    this.prendaEditable.fechaAgregado = new Date(this.fechaAgregado);

    // Colores
    this.prendaEditable.colores = this.coloresString
      .split(',')
      .map((c, index) => ({ id: index + 1, nombreColor: c.trim() } as Colores));

    // Reconstruimos los objetos anidados correctamente
    this.prendaEditable.categorias = {
      id: this.prendaEditable.categorias?.id || 1,
      nombre: this.categoriaNombre
    };

    this.prendaEditable.marcas = {
      id: this.prendaEditable.marcas?.id || 1,
      nombre: this.marcaNombre
    };

    this.prendaEditable.prendasTipo = {
      id: this.prendaEditable.prendasTipo?.id || 1,
      nombre: this.prendaTipoNombre
    };

    this.prendaEditable.usuario = {
      id: this.prendaEditable.usuario?.id || 1,
      nombreCompleto: this.usuarioNombre,
      passwordHash: this.prendaEditable.usuario?.passwordHash || '',
      nickname: this.prendaEditable.usuario?.nickname || '',
      email: this.prendaEditable.usuario?.email || ''
    };

    this.prendaEditable.imagen = {
      id: this.prendaEditable.imagen?.id || 1,
      orden: this.prendaEditable.imagen?.orden || 1,
      url_imagen: this.imagenUrl
    };

    // Enviar al backend
    this.prendaService.editarPrenda(this.prenda.titulo.toString(), this.prendaEditable)
      .subscribe({
        next: () => {
          console.log(`Prenda "${this.prendaEditable.titulo}" actualizada correctamente.`);
          this.cerrarModal(true);
        },
        error: (error) => console.error('Error al editar prenda: ', error)
      });
  }


}
