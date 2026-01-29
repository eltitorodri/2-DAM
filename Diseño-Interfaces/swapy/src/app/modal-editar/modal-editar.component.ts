import { Component, Input, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule, ModalController, ToastController, LoadingController } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { Prendas } from '../se-modelos/Prendas';
import { RelacionesService, Relacion } from '../servicios/relaciones-service';
import { PrendaService } from '../servicios/prendaService';

@Component({
  selector: 'app-modal-editar',
  templateUrl: './modal-editar.component.html',
  styleUrls: ['./modal-editar.component.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule]
})
export class ModalEditarComponent implements OnInit {

  private prendaService = inject(PrendaService);
  private modalCtrl = inject(ModalController);
  private relacionesService = inject(RelacionesService);
  private toastCtrl = inject(ToastController);
  private loadingCtrl = inject(LoadingController);

  private prendaOriginal: Prendas | undefined;

  // Variables del formulario
  idPrenda!: number;
  titulo!: string;
  descripcion!: string;
  fechaAgregado!: string;
  
  // Selectores (IDs)
  categoriaId!: number;
  marcaId!: number;
  prendaTipoId!: number;

  // Valores de texto (Deben coincidir con lo que espera Java)
  estado!: string; 
  tipoGuardado!: string;

  usuarioNombre!: string;
  imagenUrl!: string; // OJO: Esto es solo visual si el backend pide ID de imagen
  coloresString!: string;

  // Listas
  categoriasDisponibles: Relacion[] = [];
  marcasDisponibles: Relacion[] = [];
  prendasTipoDisponibles: Relacion[] = [];

  @Input()
  set prenda(value: Prendas) {
    if (value) {
      this.prendaOriginal = value;
      this.cargarDatosPrenda(value);
    }
  }

  ngOnInit(): void {
    this.cargarRelaciones();
  }

  cargarRelaciones() {
    this.relacionesService.obtenerCategorias().subscribe(data => this.categoriasDisponibles = data);
    this.relacionesService.obtenerMarcas().subscribe(data => this.marcasDisponibles = data);
    this.relacionesService.obtenerTiposPrenda().subscribe(data => this.prendasTipoDisponibles = data);
  }

  cargarDatosPrenda(prenda: Prendas) {
    this.idPrenda = prenda.id || 0;
    this.titulo = prenda.titulo || '';
    this.descripcion = prenda.descripcion || '';
    
    // Convertir fecha para el input type="date"
    this.fechaAgregado = prenda.fechaAgregado ? new Date(prenda.fechaAgregado).toISOString().split('T')[0] : '';

    this.categoriaId = prenda.categorias?.id || 0;
    this.marcaId = prenda.marcas?.id || 0;
    this.prendaTipoId = prenda.prendasTipo?.id || 0;

    // Asignar directamente lo que viene de la BD (asumiendo que viene bien)
    this.estado = prenda.estado || 'Prestamo';
    this.tipoGuardado = prenda.tipoGuardado || 'Pendiente';

    // Manejo de usuario (Nombre visual)
    this.usuarioNombre = prenda.usuario?.nombreCompleto || 'Usuario'; // Ajusta 'nombre' según tu modelo Usuario

    // Manejo de Colores
    if (prenda.colores && prenda.colores.length > 0) {
      this.coloresString = prenda.colores.map(c => c.id).join(', ');
    } else {
      this.coloresString = '';
    }
  }

  async guardarCambios() {
    if (!this.prendaOriginal) return;

    const loading = await this.mostrarLoading();

    // 1. Parsear los colores
    const coloresIds: number[] = this.coloresString
        ? this.coloresString.split(',').map(id => parseInt(id.trim(), 10)).filter(id => !isNaN(id) && id > 0)
        : [];

    // 2. Construir el DTO exacto para Spring Boot
    const prendaActualizada = {
        id: this.idPrenda, // Angular lo necesita para la URL, aunque no vaya en el Body DTO estrictamente
        titulo: this.titulo,
        descripcion: this.descripcion,
        // ENVIAMOS EL TEXTO TAL CUAL: Spring espera "Prestamo", "Intercambio", etc.
        estado: this.estado, 
        tipoGuardado: this.tipoGuardado,
        categorias: this.categoriaId,
        marcas: this.marcaId,
        prendasTipo: this.prendaTipoId,
        usuario: this.prendaOriginal.usuario?.id || null, // Mantenemos el ID original
        imagen: this.prendaOriginal.imagen?.id || null,   // Mantenemos el ID original
        colores: coloresIds
    };

    console.log('Enviando al Backend:', prendaActualizada);

    this.prendaService.actualizarPrenda(prendaActualizada).subscribe({
      next: () => {
        loading.dismiss();
        this.mostrarToast('Prenda actualizada con éxito', 'success');
        // AQUI ESTABA EL ERROR DE COMUNICACION: Usamos 'actualizado'
        this.modalCtrl.dismiss({ 'actualizado': true }); 
      },
      error: (err) => {
        loading.dismiss();
        console.error(err);
        const errorMsg = err.error?.message || 'Error al actualizar. Verifica los datos.';
        this.mostrarToast(errorMsg, 'danger');
      }
    });
  }

  cerrarModal() {
    this.modalCtrl.dismiss();
  }

  // Helpers UX
  async mostrarToast(mensaje: string, color: string) {
    const toast = await this.toastCtrl.create({ message: mensaje, duration: 3000, position: 'bottom', color: color });
    await toast.present();
  }

  async mostrarLoading() {
    const loading = await this.loadingCtrl.create({ message: 'Guardando...', duration: 5000 }); // timeout seguridad
    await loading.present();
    return loading;
  }
}