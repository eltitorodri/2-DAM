import { Component, OnInit } from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {LogoConFondoComponent} from "../logo-con-fondo/logo-con-fondo.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss'],
  standalone: true,
    imports: [
    IonicModule,
    LogoConFondoComponent,
  ]
})
export class SignUpComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
