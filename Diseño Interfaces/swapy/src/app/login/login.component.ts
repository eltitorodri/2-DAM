import { Component, OnInit } from '@angular/core';
import {IonicModule} from "@ionic/angular";
import {LogoConFondoComponent} from "../logo-con-fondo/logo-con-fondo.component";
import { Router } from '@angular/router';

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

  constructor(private router: Router) { }

  ngOnInit() {}

  isARegistro() {
    this.router.navigate(['/sign-up'])
  }

  isALogin() {
    this.router.navigate(['/home'])
  }

}
