import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IonicModule } from '@ionic/angular';

interface Product {
  id: number;
  title: string;
  subtitle: string;
  location: string;
  imageUrl: string;
}

@Component({
  selector: 'app-most-liked',
  templateUrl: './most-liked.component.html',
  styleUrls: ['./most-liked.component.scss'],
  standalone: true,
  imports: [CommonModule, IonicModule]
})
export class MostLikedComponent {

  products: Product[] = [
    {
      id: 1,
      title: 'Chaqueta LV Damier',
      subtitle: 'A Coruña',
      location: 'Cocoa Echecoa',
      imageUrl: 'https://images.pexels.com/photos/1124468/pexels-photo-1124468.jpeg?auto=compress&cs=tinysrgb&w=400'
    },
    {
      id: 2,
      title: 'Camiseta EMEstudios',
      subtitle: 'Valencia',
      location: 'Lepe Tete',
      imageUrl: 'https://images.pexels.com/photos/8532616/pexels-photo-8532616.jpeg?auto=compress&cs=tinysrgb&w=400'
    },
    {
      id: 3,
      title: 'Hoodie Cold',
      subtitle: 'Toledo',
      location: 'Tito Chape',
      imageUrl: 'https://images.pexels.com/photos/5698853/pexels-photo-5698853.jpeg?auto=compress&cs=tinysrgb&w=400'
    },
    {
      id: 4,
      title: 'Sudadera Retro',
      subtitle: 'Madrid',
      location: 'Urban Style',
      imageUrl: 'https://images.pexels.com/photos/8148577/pexels-photo-8148577.jpeg?auto=compress&cs=tinysrgb&w=400'
    },
    {
      id: 5,
      title: 'Polo Vintage',
      subtitle: 'Barcelona',
      location: 'Classic Wear',
      imageUrl: 'https://images.pexels.com/photos/1232459/pexels-photo-1232459.jpeg?auto=compress&cs=tinysrgb&w=400'
    }
  ];

  onAddProduct(product: Product): void {
    console.log('Agregar producto:', product);
  }

  onViewProfile(product: Product): void {
    console.log('Ver perfil:', product);
  }

}
