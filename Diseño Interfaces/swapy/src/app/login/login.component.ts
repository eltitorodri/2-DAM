import { Component, OnInit } from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {LogoConFondoComponent} from "../logo-con-fondo/logo-con-fondo.component";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  standalone: true,
    imports: [
    IonicModule,
    LogoConFondoComponent,
  ]
})
export class LoginComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

  

}
