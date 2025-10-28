import { Component, OnInit } from '@angular/core';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { LogoSloganComponent } from "../logo-slogan/logo-slogan.component";

@Component({
  selector: 'app-login',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
  imports: [NavegationBarComponent, LogoSloganComponent],
})

export class HomeComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
