import { Component, OnInit } from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {LogoConFondoComponent} from "../logo-con-fondo/logo-con-fondo.component";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
  imports: [
    IonicModule,
    LogoConFondoComponent,

  ]
})

export class HomeComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
