import { Component, OnInit } from '@angular/core';
import { IonHeader } from "@ionic/angular/standalone";

@Component({
  selector: 'app-logo-slogan',
  templateUrl: './logo-slogan.component.html',
  styleUrls: ['./logo-slogan.component.scss'],
  standalone: true,
  imports: [
    IonHeader,
  ]
})
export class LogoSloganComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
