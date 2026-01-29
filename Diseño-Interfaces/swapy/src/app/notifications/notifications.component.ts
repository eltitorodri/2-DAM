import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

interface ChatItem {
  id: number;
  title: string;
  subtitle: string;
  time: string;
  isFavorite: boolean;
  badgeLabel: string;
}

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.scss'],
  standalone: true,
  imports: [CommonModule]
})
export class NotificationsComponent {
  chats: ChatItem[] = [
    { 
      id: 1, 
      title: '"Camiseta EMEstudios" esta triunfando!', 
      time: 'Hace 5 horas', 
      isFavorite: true,
      badgeLabel: 'Guardados',
      subtitle: ''
    },
    { 
      id: 2, 
      title: '"Camiseta EMEstudios" esta triunfando!', 
      time: 'Hace 5 horas', 
      isFavorite: false,
      badgeLabel: '+100 visualizaciones',
      subtitle: ''
    },
    { 
      id: 3, 
      title: '"Camiseta EMEstudios" esta triunfando!', 
      time: 'Hace 5 horas', 
      isFavorite: true,
      badgeLabel: 'Guardados',
      subtitle: ''
    },
    { 
      id: 4, 
      title: '"Camiseta EMEstudios" esta triunfando!', 
      time: 'Hace 5 horas', 
      isFavorite: false,
      badgeLabel: '+100 visualizaciones',
      subtitle: ''
    },
    { 
      id: 5, 
      title: '"Camiseta EMEstudios" esta triunfando!', 
      time: 'Hace 5 horas', 
      isFavorite: true,
      badgeLabel: 'Guardados',
      subtitle: ''
    },
  ];

  constructor(private router: Router) {}

  goToNotifications(): void {
    this.router.navigate(['/pageChats']);
  }

}
