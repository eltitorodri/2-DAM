import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule, AlertController, ModalController } from '@ionic/angular';
import { PrendaService } from '../servicios/prendaService';
import { PrendasItem } from '../se-modelos/PrendasItem';
import { ModalEditarComponent } from '../modal-editar/modal-editar.component';

@Component({
  selector: 'app-trend-topic',
  templateUrl: './trend-topic.component.html',
  styleUrls: ['./trend-topic.component.scss'],
  standalone: true,
  imports: [CommonModule, IonicModule]
})
export class TrendTopicComponent {
  private prendaService = inject(PrendaService);
  private alertController = inject(AlertController);
  private modalCtrl = inject(ModalController);

  public prendas: PrendasItem[] = [];

  constructor() {}

  ngOnInit() {
    this.cargarPrendas();
  }

  cargarPrendas(): void {
    this.prendaService.obtenerPrendas().subscribe({
      next: (data) => {
        console.log('Datos recibidos: ', data);
        this.prendas = data;
      },
      error: (error: any) => console.error('Error: ', error),
      complete: () => console.log('Solicitud completada')
    });
  }

  async borrarPrenda(titulo: any) {
    const tituloStr: string = titulo.toString(); 
    const alert = await this.alertController.create({
      header: 'Confirmar',
      message: `¿Estás seguro de que quieres eliminar la prenda "${tituloStr}"?`,
      buttons: [
        { text: 'Cancelar', role: 'cancel', handler: () => console.log('Borrado cancelado') },
        { text: 'Borrar', role: 'destructive', handler: () => {
            this.prendaService.eliminarPrenda(tituloStr).subscribe({
              next: () => { console.log(`Prenda con nombre ${tituloStr} eliminada`); this.cargarPrendas(); },
              error: (error: any) => console.error('Error al eliminar: ', error),
              complete: () => console.log('Eliminación completada')
            });
          }
        }
      ]
    });
    await alert.present();
  }

  async abrirEditarModal(prenda: PrendasItem) {
    if (!prenda) return;

    const modal = await this.modalCtrl.create({
      component: ModalEditarComponent,
      componentProps: { prenda }
    });

    modal.onDidDismiss().then((data) => {
      if (data.data?.actualizado) {
        this.cargarPrendas(); // Recargamos si se actualizó la prenda
      }
    });

    await modal.present();
  }
}
