import { Component } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { addCircle, person } from 'ionicons/icons';


@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  imports: [IonApp, IonRouterOutlet],
  standalone: true 
})
export class AppComponent {
  constructor() {
    addIcons({ addCircle, person });
  }
}
