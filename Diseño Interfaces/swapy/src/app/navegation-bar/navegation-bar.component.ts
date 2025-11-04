import { Component, OnInit } from '@angular/core';
import { IonFooter, IonToolbar, IonIcon, IonButton } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { Router, RouterLink } from '@angular/router';
import { homeOutline, heartOutline, addCircleOutline, chatbubblesOutline, personCircleOutline } from 'ionicons/icons';

@Component({
  selector: 'app-navegation-bar',
  templateUrl: './navegation-bar.component.html',
  styleUrls: ['./navegation-bar.component.scss'],
  standalone: true,
  imports: [IonButton,  IonFooter, IonToolbar, IonIcon, RouterLink],
})
export class NavegationBarComponent implements OnInit {

  constructor(private router: Router) {
    addIcons({ 
      homeOutline, 
      heartOutline, 
      addCircleOutline, 
      chatbubblesOutline, 
      personCircleOutline 
    });
  }

  goToSaved() : void {
    this.router.navigate(['/savedSections'])
  }

  goToUpload() : void {
    this.router.navigate(['/uploadCloth1'])
  }
  
  ngOnInit() {}

}
