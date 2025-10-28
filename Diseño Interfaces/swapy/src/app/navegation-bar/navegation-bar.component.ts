import { Component, OnInit } from '@angular/core';
import { IonFooter, IonToolbar, IonTabBar, IonTabButton, IonIcon } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { homeOutline, heartOutline, addCircleOutline, chatbubblesOutline, personCircleOutline } from 'ionicons/icons';

@Component({
  selector: 'app-navegation-bar',
  templateUrl: './navegation-bar.component.html',
  styleUrls: ['./navegation-bar.component.scss'],
  standalone: true,
  imports: [IonFooter, IonToolbar, IonTabBar, IonTabButton, IonIcon],
})
export class NavegationBarComponent implements OnInit {

  constructor() {
    addIcons({ 
      homeOutline, 
      heartOutline, 
      addCircleOutline, 
      chatbubblesOutline, 
      personCircleOutline 
    });
  }

  ngOnInit() {}

}
