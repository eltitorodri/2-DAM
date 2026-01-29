import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule, ModalController, ToastController, LoadingController } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { RelacionesService, Relacion } from '../servicios/relaciones-service';
import { PrendaService } from '../servicios/prendaService';

@Component({
  selector: 'app-modal-crear',
  templateUrl: './modal-crear.component.html',
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule]
})
export class ModalCrearComponent implements OnInit {

  private prendaService = inject(PrendaService);
  private modalCtrl = inject(ModalController);
  private relacionesService = inject(RelacionesService);
  private toastCtrl = inject(ToastController);
  private loadingCtrl = inject(LoadingController);

  titulo: string = '';
  descripcion: string = '';

  estado: string = 'Prestamo'; 
  tipoGuardado: string = 'Pendiente';

  categoriaId!: number;
  marcaId!: number;
  prendaTipoId!: number;

  usuarioId!: number; 
  imagenId!: number;

  coloresString: string = '';

  categoriasDisponibles: Relacion[] = [];
  marcasDisponibles: Relacion[] = [];
  prendasTipoDisponibles: Relacion[] = [];

  ngOnInit(): void {
    this.cargarRelaciones();
  }

  cargarRelaciones() {
    this.relacionesService.obtenerCategorias().subscribe(data => this.categoriasDisponibles = data);
    this.relacionesService.obtenerMarcas().subscribe(data => this.marcasDisponibles = data);
    this.relacionesService.obtenerTiposPrenda().subscribe(data => this.prendasTipoDisponibles = data);
  }

  async guardarPrenda() {
    const loading = await this.mostrarLoading();

    const coloresIds: number[] = this.coloresString
        ? this.coloresString.split(',').map(id => parseInt(id.trim(), 10)).filter(id => !isNaN(id) && id > 0)
        : [];

    const nuevaPrenda = {
        titulo: this.titulo,
        descripcion: this.descripcion,
        estado: this.estado,
        tipoGuardado: this.tipoGuardado,
        categorias: this.categoriaId,
        marcas: this.marcaId,
        prendasTipo: this.prendaTipoId,
        usuario: this.usuarioId, 
        imagen: this.imagenId,   
        colores: coloresIds
    };

    console.log('Enviando a crear:', nuevaPrenda);

    this.prendaService.crearPrenda(nuevaPrenda).subscribe({
      next: (resp) => {
        loading.dismiss();
        this.mostrarToast('Prenda creada con éxito', 'success');
        this.modalCtrl.dismiss({ 'creado': true });
      },
      error: (err) => {
        loading.dismiss();
        console.error(err);
        const errorMsg = err.error?.message || 'Error al crear. Revisa los campos (IDs válidos).';
        this.mostrarToast(errorMsg, 'danger');
      }
    });
  }

  cerrarModal() {
    this.modalCtrl.dismiss();
  }

  async mostrarToast(mensaje: string, color: string) {
    const toast = await this.toastCtrl.create({ message: mensaje, duration: 3000, position: 'bottom', color: color });
    await toast.present();
  }

  async mostrarLoading() {
    const loading = await this.loadingCtrl.create({ message: 'Publicando prenda...', duration: 5000 });
    await loading.present();
    return loading;
  }
}