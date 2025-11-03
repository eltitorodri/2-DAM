import { Component } from '@angular/core';
import { IonHeader, IonIcon, IonToolbar, IonTitle, IonContent, IonList, IonItem, IonLabel } from "@ionic/angular/standalone";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.scss'],
  standalone: true,
  imports: ([CommonModule, IonHeader, IonIcon, IonToolbar, IonTitle, IonContent, IonList, IonItem, IonLabel]),
})
export class SettingsComponent {
  userProfile = {
    name: 'Ramona L.',
    avatar: 'https://images.unsplash.com/photo-1494790108377-be9c29b29330?w=200&h=200&fit=crop',
    rating: 5,
    reviewCount: 5,
    joinDate: '2025'
  };

  menuItems = [
    { label: 'Editar perfil', icon: 'chevron-forward' },
    { label: 'Préstamos', icon: 'chevron-forward' },
    { label: 'Intercambios', icon: 'chevron-forward' },
    { label: 'Intercambios', icon: 'chevron-forward' }
  ];

  verificationItems = [
    { label: 'Verificación en dos pasos', hasQR: false },
    { label: 'Verificación Email', hasQR: false },
    { label: 'Cambiar contraseña', hasQR: false }
  ];

  supportItems = [
    { label: 'Chat de soporte', icon: 'chevron-forward' },
    { label: '¿Necesitas ayuda?', icon: 'chevron-forward' }
  ];

  constructor() {}
}