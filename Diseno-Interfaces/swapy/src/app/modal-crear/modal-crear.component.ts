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
  coloresString: string = ''; // Ej: "1, 2"

  categoriasDisponibles: Relacion[] = [];
  marcasDisponibles: Relacion[] = [];
  prendasTipoDisponibles: Relacion[] = [];

  // Cámara
  @ViewChild('video') video!: ElementRef<HTMLVideoElement>;
  @ViewChild('canvas') canvas!: ElementRef<HTMLCanvasElement>;
  stream: MediaStream | null = null;
  fotoCapturada: string | null = null; // Base64 raw
  fotoCapturadaSafe: SafeResourceUrl | null = null; // Para mostrar en HTML

  ngOnInit(): void {
    this.cargarRelaciones();
  }

  cargarRelaciones() {
    this.relacionesService.obtenerCategorias().subscribe(data => this.categoriasDisponibles = data);
    this.relacionesService.obtenerMarcas().subscribe(data => this.marcasDisponibles = data);
    this.relacionesService.obtenerTiposPrenda().subscribe(data => this.prendasTipoDisponibles = data);
  }

  // --- LÓGICA DE CÁMARA ---

  async iniciarCamara() {
    try {
      this.stream = await navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } });
      this.video.nativeElement.srcObject = this.stream;
    } catch (err) {
      console.error(err);
      this.mostrarToast('Error al acceder a la cámara. Revisa los permisos.', 'danger');
    }
  }

  tomarFoto() {
    const context = this.canvas.nativeElement.getContext('2d');
    if (context && this.video.nativeElement.videoWidth) {
      this.canvas.nativeElement.width = this.video.nativeElement.videoWidth;
      this.canvas.nativeElement.height = this.video.nativeElement.videoHeight;
      
      // Dibujar imagen en canvas
      context.drawImage(this.video.nativeElement, 0, 0);
      
      // Obtener Base64
      this.fotoCapturada = this.canvas.nativeElement.toDataURL('image/jpeg', 0.8); // 0.8 calidad
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

  // --- LÓGICA DE ENVÍO (Cloudinary + Spring Boot) ---

  async guardarPrenda() {
    // 1. Validaciones
    if (!this.fotoCapturada) {
      this.mostrarToast('¡Debes tomar una foto!', 'warning');
      return;
    }
    if (!this.titulo || !this.categoriaId || !this.marcaId || !this.usuarioId) {
      this.mostrarToast('Completa los campos obligatorios', 'warning');
      return;
    }

    const loading = await this.loadingCtrl.create({ message: 'Subiendo a la nube...' });
    await loading.present();

    try {
      // 2. Convertir Base64 a Blob (Archivo)
      const archivoBlob = this.dataURItoBlob(this.fotoCapturada);

      // 3. Crear FormData
      const formData = new FormData();
      
      // Archivo (El nombre 'file' debe coincidir con @RequestParam("file") en Java)
      formData.append('file', archivoBlob, 'foto_camara.jpg');

      // Campos de texto (Coinciden con @RequestParam en Java)
      formData.append('titulo', this.titulo);
      formData.append('descripcion', this.descripcion);
      formData.append('estado', this.estado);
      formData.append('tipoGuardado', this.tipoGuardado);
      formData.append('usuario', String(this.usuarioId));
      formData.append('categorias', String(this.categoriaId));
      formData.append('marcas', String(this.marcaId));
      formData.append('prendasTipo', String(this.prendaTipoId));

      if (this.coloresString) {
        formData.append('colores', this.coloresString);
      }

      console.log('Enviando FormData...');

      // 4. Enviar al Backend
      this.prendaService.crearPrenda(formData).subscribe({
        next: (res) => {
          loading.dismiss();
          this.mostrarToast('¡Prenda publicada con éxito!', 'success');
          this.modalCtrl.dismiss({ creado: true });
        },
        error: (err) => {
          loading.dismiss();
          console.error('Error Backend:', err);
          this.mostrarToast('Error al guardar. Revisa la consola.', 'danger');
        }
      });

    } catch (e) {
      loading.dismiss();
      console.error(e);
      this.mostrarToast('Error procesando la imagen', 'danger');
    }
  }

  // Utilidad para convertir el string de la cámara en un archivo real
  dataURItoBlob(dataURI: string) {
    const byteString = atob(dataURI.split(',')[1]);
    const mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
    const ab = new ArrayBuffer(byteString.length);
    const ia = new Uint8Array(ab);
    for (let i = 0; i < byteString.length; i++) {
      ia[i] = byteString.charCodeAt(i);
    }
    return new Blob([ab], { type: mimeString });
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