import { Component, OnInit } from '@angular/core';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { LogoSloganComponent } from "../logo-slogan/logo-slogan.component";
<<<<<<< HEAD
import { IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle } from '@ionic/angular/standalone'; 
import { ProductSliderComponent } from '../product-slider/product-slider.component'; 
import { TrendTopicComponent } from '../trend-topic/trend-topic.component'; 
=======
import { ProductSliderComponent } from '../product-slider/product-slider.component';
import { TrendTopicComponent } from "../trend-topic/trend-topic.component";
import { IonContent } from "@ionic/angular/standalone";
import { MostLikedComponent } from "../most-liked/most-liked.component";
import { SearcherComponent } from "../searcher/searcher.component";
>>>>>>> 020a5376a6107b8f057b99129f64c5b2975cc670

@Component({
  selector: 'app-login',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
<<<<<<< HEAD
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
=======
  imports: [IonContent, NavegationBarComponent,
    LogoSloganComponent,
    ProductSliderComponent, TrendTopicComponent, MostLikedComponent, SearcherComponent],
>>>>>>> 020a5376a6107b8f057b99129f64c5b2975cc670
})

export class HomeComponent  implements OnInit {

  constructor() {}

  ngOnInit() {}

}
