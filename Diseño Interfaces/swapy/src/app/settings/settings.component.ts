import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { LogoSloganComponent } from "../logo-slogan/logo-slogan.component";
import { SloganLogoComponent } from "../slogan-logo/slogan-logo.component";

@Component({
  selector: 'app-settings',
  standalone: true,
  imports: [CommonModule, NavegationBarComponent, SloganLogoComponent],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.scss'
})
export class SettingsComponent {
  userProfile = {
    avatar: 'https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?w=96&h=96&fit=crop',
    name: 'Juan Pérez',
    rating: 5,
    reviewCount: 5,
    joinDate: 'Diciembre 2024'
  };

  menuItems = [
    'Editar perfil',
    'Préstamos',
    'Intercambios',
    'Historial'
  ];

  verificationItems = [
    'Verificación en dos pasos',
    'Verificación Email',
    'Cambiar contraseña',
    'Chat de soporte',
    'Necesitas ayuda?'
  ];
}

