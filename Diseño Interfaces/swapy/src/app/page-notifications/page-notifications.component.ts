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
  selector: 'app-page-notifications',
  templateUrl: './page-notifications.component.html',
  styleUrls: ['./page-notifications.component.scss'],
  standalone: true,
  imports: ([CommonModule])
})
export class PageNotificationsComponent {

  

}
