import { Component, OnInit } from '@angular/core';
import { IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle } from '@ionic/angular/standalone';

@Component({
  selector: 'app-cloth-card',
  templateUrl: './cloth-card.component.html',
  styleUrls: ['./cloth-card.component.scss'],
  standalone: true,
  imports: [IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle],
})
export class ClothCardComponent  implements OnInit {

  constructor() { }

  ngOnInit() {}

}
