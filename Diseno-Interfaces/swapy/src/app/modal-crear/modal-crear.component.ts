import { Component, ElementRef, inject, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule, ModalController, ToastController, LoadingController } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { RelacionesService, Relacion } from '../servicios/relaciones-service';
import { PrendaService } from '../servicios/prendaService';

@Component({
  selector: 'app-modal-crear',
  templateUrl: './modal-crear.component.html',
  styleUrls: ['./modal-crear.component.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule]
})
export class ModalCrearComponent implements OnInit {
  private prendaService = inject(PrendaService);
  private modalCtrl = inject(ModalController);
  private relacionesService = inject(RelacionesService);
  private toastCtrl = inject(ToastController);
  private loadingCtrl = inject(LoadingController);
  private sanitizer = inject(DomSanitizer);

  // Formulario
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

  // Cámara
  @ViewChild('video') video!: ElementRef<HTMLVideoElement>;
  @ViewChild('canvas') canvas!: ElementRef<HTMLCanvasElement>;
  stream: MediaStream | null = null;
  fotoCapturada: string | null = null;
  fotoCapturadaSafe: SafeResourceUrl | null = null;

  ngOnInit(): void {
    this.cargarRelaciones();
  }

  cargarRelaciones() {
    this.relacionesService.obtenerCategorias().subscribe(data => this.categoriasDisponibles = data);
    this.relacionesService.obtenerMarcas().subscribe(data => this.marcasDisponibles = data);
    this.relacionesService.obtenerTiposPrenda().subscribe(data => this.prendasTipoDisponibles = data);
  }

  async iniciarCamara() {
    try {
      this.stream = await navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } });
      this.video.nativeElement.srcObject = this.stream;
    } catch (err) {
      this.mostrarToast('Error al acceder a la cámara', 'danger');
    }
  }

  tomarFoto() {
    const context = this.canvas.nativeElement.getContext('2d');
    if (context) {
      this.canvas.nativeElement.width = this.video.nativeElement.videoWidth;
      this.canvas.nativeElement.height = this.video.nativeElement.videoHeight;
      context.drawImage(this.video.nativeElement, 0, 0);
      this.fotoCapturada = this.canvas.nativeElement.toDataURL('image/jpeg');
      this.fotoCapturadaSafe = this.sanitizer.bypassSecurityTrustResourceUrl(this.fotoCapturada);
      this.detenerCamara();
    }
  }

  detenerCamara() {
    if (this.stream) {
      this.stream.getTracks().forEach(track => track.stop());
      this.stream = null;
    }
  }

  reintentarFoto() {
    this.fotoCapturada = null;
    this.fotoCapturadaSafe = null;
    this.iniciarCamara();
  }

  async guardarPrenda() {
    const loading = await this.loadingCtrl.create({ message: 'Enviando datos...' });
    await loading.present();

    const coloresIds: number[] = this.coloresString
        ? this.coloresString.split(',').map(id => parseInt(id.trim(), 10)).filter(id => !isNaN(id) && id > 0)
        : [];

    const nuevaPrenda = {
        titulo: this.titulo,
        descripcion: this.descripcion,
        estado: this.estado,
        tipoGuardado: this.tipoGuardado,
        categorias: Number(this.categoriaId),
        marcas: Number(this.marcaId),
        prendasTipo: Number(this.prendaTipoId),
        usuario: Number(this.usuarioId),
        imagen: Number(this.imagenId),
        colores: coloresIds
    };

    console.log('Build Dev - Enviando:', nuevaPrenda);

    this.prendaService.crearPrenda(nuevaPrenda).subscribe({
      next: () => {
        loading.dismiss();
        this.detenerCamara();
        this.mostrarToast('¡Prenda creada con éxito!', 'success');
        this.modalCtrl.dismiss({ creado: true });
      },
      error: (err) => {
        loading.dismiss();
        console.error('Error 500:', err);
        this.mostrarToast('Error 500: Revisa los IDs de la Base de Datos', 'danger');
      }
    });
  }

  cerrarModal() {
    this.detenerCamara();
    this.modalCtrl.dismiss();
  }

  async mostrarToast(mensaje: string, color: string) {
    const toast = await this.toastCtrl.create({ message: mensaje, duration: 3000, color: color });
    await toast.present();
  }
}