import { Component, OnInit } from '@angular/core';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { LogoSloganComponent } from "../logo-slogan/logo-slogan.component";
import { ProductSliderComponent } from '../product-slider/product-slider.component';
import { TrendTopicComponent } from "../trend-topic/trend-topic.component";
import { IonContent } from "@ionic/angular/standalone";
import { MostLikedComponent } from "../most-liked/most-liked.component";
import { SearcherComponent } from "../searcher/searcher.component";

@Component({
  selector: 'app-login',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  standalone: true,
  imports: [IonContent, NavegationBarComponent,
    LogoSloganComponent,
    ProductSliderComponent, TrendTopicComponent, MostLikedComponent, SearcherComponent],
})

export class HomeComponent  implements OnInit {

  constructor() {}

  ngOnInit() {}

}
