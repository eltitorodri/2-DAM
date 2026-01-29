import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

interface ChatItem {
  id: number;
  name: string;
  title: string;
  time: string;
  avatarText?: string;
  subtitle?: string;
}

@Component({
  selector: 'app-chats',
  templateUrl: './chats.component.html',
  styleUrls: ['./chats.component.scss'],
  standalone: true,
  imports: [CommonModule]
})
export class ChatsComponent {
  chats: ChatItem[] = [
    { id: 1, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: 'Hace 5 horas' },
    { id: 2, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: 'Hace 5 horas' },
    { id: 3, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: 'Hace 5 horas' },
    { id: 4, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: 'Hace 5 horas' },
    { id: 5, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: 'Hace 5 horas' },
  ];

  constructor(private router: Router) {}

  goToNotifications(): void {
    this.router.navigate(['/pageNotifications']);
  }

  insideTheChat(): void {
    this.router.navigate(['/chat']);
  }

}
