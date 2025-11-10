import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

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
  imports: ([CommonModule])
})
export class ChatsComponent {
  chats: ChatItem[] = [
    { id: 1, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: '⭐ Héctor Bellerín' },
    { id: 2, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: '⭐ Héctor Bellerín' },
    { id: 3, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: '⭐ Héctor Bellerín' },
    { id: 4, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: '⭐ Héctor Bellerín' },
    { id: 5, name: 'Héctor Bellerín', title: 'Camiseta EMEstudios', time: '22:13', avatarText: 'HB', subtitle: '⭐ Héctor Bellerín' },
  ];
}
