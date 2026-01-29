import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {LogoConFondoComponent} from "../logo-con-fondo/logo-con-fondo.component";
import {IonicModule} from "@ionic/angular";

@Component({
  selector: 'app-splash',
  templateUrl: './splash.component.html',
  styleUrls: ['./splash.component.scss'],
    standalone: true,
      imports: [
      IonicModule,
      LogoConFondoComponent,
    ]
})
export class SplashComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {
    setTimeout(() => {
      this.router.navigate(['/login']);
    }, 3000);
  }

}
