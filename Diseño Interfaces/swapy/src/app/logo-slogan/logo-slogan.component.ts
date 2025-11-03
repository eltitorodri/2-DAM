import { Component, OnInit } from '@angular/core';
import { IonHeader } from "@ionic/angular/standalone";
import { CarrouselHorizontalComponent } from "../carrousel-horizontal/carrousel-horizontal.component";

@Component({
  selector: 'app-logo-slogan',
  templateUrl: './logo-slogan.component.html',
  styleUrls: ['./logo-slogan.component.scss'],
  standalone: true,
  imports: [
    CarrouselHorizontalComponent
]
})
export class LogoSloganComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
