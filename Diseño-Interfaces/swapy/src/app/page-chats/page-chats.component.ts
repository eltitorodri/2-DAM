import { Component, OnInit } from '@angular/core';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { SloganLogoComponent } from "../slogan-logo/slogan-logo.component";
import { ChatsComponent } from "../chats/chats.component";

@Component({
  selector: 'app-page-chats',
  templateUrl: './page-chats.component.html',
  styleUrls: ['./page-chats.component.scss'],
  standalone: true,
  imports: [NavegationBarComponent, SloganLogoComponent, ChatsComponent],
})
export class PageChatsComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
