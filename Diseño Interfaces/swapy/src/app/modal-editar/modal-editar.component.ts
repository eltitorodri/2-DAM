// Archivo: src/app/modal-editar/modal-editar.component.ts

import { Component, Input, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
// Importaciones de UX: ToastController y LoadingController
import { IonicModule, ModalController, ToastController, LoadingController } from '@ionic/angular'; 
import { FormsModule } from '@angular/forms'; 

// Modelos y Servicios
import { Prendas } from '../se-modelos/Prendas'; 
import { RelacionesService, Relacion } from '../servicios/relaciones-service'; 
import { PrendaService } from '../servicios/prendaService';

// --- DEFINICIONES DE TIPOS (Usamos los valores largos para la UI, pero enviaremos los cortos) ---
type EstadoPrenda = 'Prestamo' | 'Intercambio' | 'pendiente' | 'aceptada' | 'finalizada' | 'rechazada';
type TipoGuardadoPrenda = 'Guardado' | 'Pendiente';

// --- Códigos que la DB acepta (Mapeo Final para evitar Data Truncation) ---
const CODIGOS_ESTADO = {
    'Prestamo': 'PRSTM',       // 5 caracteres
    'Intercambio': 'INTCB',     // 5 caracteres
    'pendiente': 'PNDTE',
    'aceptada': 'ACPDA',
    'finalizada': 'FNLDA',
    'rechazada': 'RCHZD',
};

const CODIGOS_TIPOGUARDADO = {
    'Guardado': 'GUARD',        // 6 caracteres
    'Pendiente': 'PNDTE',       // 5 caracteres
};


@Component({
    selector: 'app-modal-editar',
    templateUrl: './modal-editar.component.html',
    styleUrls: ['./modal-editar.component.scss'],
    standalone: true,
    imports: [IonicModule, CommonModule, FormsModule]
})
export class ModalEditarComponent implements OnInit {

    // INYECCIONES
    private prendaService = inject(PrendaService);
    private modalCtrl = inject(ModalController);
    private relacionesService = inject(RelacionesService); 
    private toastCtrl = inject(ToastController);     // INYECCIÓN UX
    private loadingCtrl = inject(LoadingController); // INYECCIÓN UX

    private prendaOriginal: Prendas | undefined; 

    // --- VARIABLES DE ENLACE Y PRECARGA ---
    idPrenda!: number;
    titulo!: string;
    descripcion!: string;
    fechaAgregado!: string;
    coloresString!: string; 
    
    // IDs para los selectores (ngModel)
    categoriaId!: number;
    marcaId!: number;
    prendaTipoId!: number;
    
    // Valores literales (usan los strings largos para la UI)
    estado: EstadoPrenda = 'Prestamo';
    tipoGuardado: TipoGuardadoPrenda = 'Pendiente';

    usuarioNombre!: string; 
    imagenUrl!: string;

    // --- LISTAS PARA LOS SELECTS ---
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
        
        this.fechaAgregado = prenda.fechaAgregado ? new Date(prenda.fechaAgregado).toISOString() : new Date().toISOString();
        
        this.categoriaId = prenda.categorias?.id || 0; 
        this.marcaId = prenda.marcas?.id || 0;
        this.prendaTipoId = prenda.prendasTipo?.id || 0;
        
        // Asignación de Estados/Tipos
        this.estado = (prenda.estado as EstadoPrenda) || 'Prestamo';
        this.tipoGuardado = (prenda.tipoGuardado as TipoGuardadoPrenda) || 'Pendiente';

        // Manejo de colores
        if (prenda.colores && prenda.colores.length > 0) {
            this.coloresString = prenda.colores.map(c => c.id).join(', ');
        } else {
            this.coloresString = '';
        }
    }

    // --- FUNCIONES AUXILIARES DE UX ---

    async mostrarToast(mensaje: string, color: string = 'success') {
        const toast = await this.toastCtrl.create({
            message: mensaje,
            duration: 3000,
            position: 'bottom',
            color: color
        });
        await toast.present();
    }

    async mostrarLoading(mensaje: string = 'Guardando cambios...') {
        const loading = await this.loadingCtrl.create({
            message: mensaje,
            duration: 0, 
        });
        await loading.present();
        return loading;
    }

    // --- FUNCIÓN GUARDAR CAMBIOS: IMPLEMENTACIÓN DEL MAPEO DE CÓDIGOS CORTOS Y UX ---
    
    async guardarCambios() {
        
        if (!this.prendaOriginal) return; 

        const loading = await this.mostrarLoading(); // Muestra el indicador de carga

        // 1. Mapeo del Estado y TipoGuardado a CÓDIGOS CORTOS
        const estadoMapeado = CODIGOS_ESTADO[this.estado as keyof typeof CODIGOS_ESTADO] || this.estado;
        const tipoGuardadoMapeado = CODIGOS_TIPOGUARDADO[this.tipoGuardado as keyof typeof CODIGOS_TIPOGUARDADO] || this.tipoGuardado;

        // 2. Preparación de IDs de Colores (Filtra IDs inválidos/cero)
        const coloresIds: number[] = this.coloresString
            .split(',')
            .map(id => parseInt(id.trim(), 10))
            .filter(id => !isNaN(id) && id > 0);
        
        const coloresAEnviar = coloresIds.length > 0 ? coloresIds : [];

        // 3. Creación del DTO de envío
        const prendaActualizada: any = { 
            id: this.idPrenda, 
            titulo: this.titulo,
            descripcion: this.descripcion,
            
            // Códigos cortos mapeados para Spring Boot
            estado: estadoMapeado,
            tipoGuardado: tipoGuardadoMapeado,
            
            // IDs para las relaciones
            categorias: this.categoriaId, 
            marcas: this.marcaId,
            prendasTipo: this.prendaTipoId,
            usuario: this.prendaOriginal.usuario?.id || null, 
            imagen: this.prendaOriginal.imagen?.id || null, 
            
            // Lista de IDs de Colores
            colores: coloresAEnviar,
        }; 
        
        console.log('JSON enviado a Spring Boot (Prenda Actualizada):', prendaActualizada);

        // Llamada al servicio
        this.prendaService.actualizarPrenda(prendaActualizada).subscribe({
            next: (respuesta: any) => {
                loading.dismiss(); // Oculta la carga
                this.mostrarToast('Prenda actualizada con éxito', 'success');
                // Cierra el modal con la señal de éxito
                this.modalCtrl.dismiss({ 'updated': true });
            },
            error: (err: any) => {
                loading.dismiss(); // Oculta la carga
                
                // Intenta mostrar un mensaje de error más específico
                const errorMessage = err.error?.message || 'Error al actualizar. Revise los datos y el servidor.';
                this.mostrarToast(errorMessage, 'danger');
                
                console.error('Error al actualizar la prenda:', err);
            }
        });
    }

    cerrarModal() {
        this.modalCtrl.dismiss();
    }
}