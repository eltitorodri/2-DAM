import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-carrousel-horizontal',
  templateUrl: './carrousel-horizontal.component.html',
  styleUrls: ['./carrousel-horizontal.component.scss'],
  standalone: true,
  imports: [CommonModule]
})
export class CarrouselHorizontalComponent {

  categories: string[] = [
    'Nike',
    'Baggy',
    'Streetwear',
    'Adidas',
    'Vintage',
    'Oversized',
    'Y2K',
    'Cargo',
    'Denim',
    'Sports'
  ];

}
