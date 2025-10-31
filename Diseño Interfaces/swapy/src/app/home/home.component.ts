import { Component, OnInit } from '@angular/core';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { LogoSloganComponent } from "../logo-slogan/logo-slogan.component";
import { IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle } from '@ionic/angular/standalone'; 
import { ProductSliderComponent } from '../product-slider/product-slider.component'; 
import { TrendTopicComponent } from '../trend-topic/trend-topic.component'; 

@Component({
  selector: 'app-login',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
  imports: [NavegationBarComponent, 
    LogoSloganComponent, 
    IonCard, 
    IonCardContent, 
    IonCardHeader, 
    IonCardSubtitle, 
    IonCardTitle,
    ProductSliderComponent,
    TrendTopicComponent
  ],
})

export class HomeComponent  implements OnInit {

  constructor() {}

  ngOnInit() {}

}
