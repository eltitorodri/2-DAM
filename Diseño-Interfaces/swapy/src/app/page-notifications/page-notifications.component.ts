import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SloganLogoComponent } from "../slogan-logo/slogan-logo.component";
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { NotificationsComponent } from "../notifications/notifications.component";

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
  imports: ([CommonModule, NotificationsComponent, NavegationBarComponent,SloganLogoComponent])
})
export class PageNotificationsComponent {

  

}
