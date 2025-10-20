import { Component, OnInit } from '@angular/core';
import {IonFooter, IonTitle, IonToolbar } from '@ionic/angular/standalone';

@Component({
  selector: 'app-navegation-bar',
  templateUrl: './navegation-bar.component.html',
  styleUrls: ['./navegation-bar.component.scss'],
  standalone: true,
  imports: [IonFooter, IonTitle, IonToolbar],
})
export class NavegationBarComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
