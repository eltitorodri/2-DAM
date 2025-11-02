import { Component } from '@angular/core';
import { SloganLogoComponent } from "../slogan-logo/slogan-logo.component";
import { CommonModule } from '@angular/common';
import { IonSegment, IonSegmentButton, IonLabel, IonIcon, IonButton, IonContent } from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { heart, personCircle } from 'ionicons/icons';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { SearcherComponent } from "../searcher/searcher.component";

addIcons({ heart, personCircle });

interface SavedProduct {
  id: number;
  brand: string;
  name: string;
  image: string;
  size: string;
  status: string;
  rating?: number;
}

interface SavedProfile {
  id: number;
  name: string;
  username: string;
  avatar: string;
  friends: number;
  categories: string[];
  rating: number;
}

@Component({
  selector: 'app-saved-sections',
  templateUrl: './saved-sections.component.html',
  styleUrls: ['./saved-sections.component.scss'],
  standalone: true,
  imports: [SloganLogoComponent, CommonModule,
    IonIcon,
    NavegationBarComponent, SearcherComponent],
})
export class SavedSectionsComponent {

activeTab = 'pendientes';

  savedProducts: SavedProduct[] = [
    {
      id: 1,
      brand: 'Camiseta EMEStudios',
      name: 'Valencia',
      image: 'https://images.pexels.com/photos/3622622/pexels-photo-3622622.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'L',
      status: 'Perfecto'
    },
    {
      id: 2,
      brand: 'Chaqueta LV Damier',
      name: 'A Coruña',
      image: 'https://images.pexels.com/photos/1926769/pexels-photo-1926769.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'XL',
      status: 'Nuevo'
    },
    {
      id: 3,
      brand: 'Hoodie Cold Culture',
      name: 'Toledo',
      image: 'https://images.pexels.com/photos/3621436/pexels-photo-3621436.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'S',
      status: 'Acceptable'
    },
    {
      id: 4,
      brand: 'Camiseta EMEStudios',
      name: 'Valencia',
      image: 'https://images.pexels.com/photos/3622622/pexels-photo-3622622.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'L',
      status: 'Perfecto'
    }
  ];

  likedProducts: SavedProduct[] = [
    {
      id: 1,
      brand: 'Camiseta EMEStudios',
      name: 'Valencia',
      image: 'https://images.pexels.com/photos/3622622/pexels-photo-3622622.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'L',
      status: 'Perfecto'
    },
    {
      id: 2,
      brand: 'Chaqueta LV Damier',
      name: 'A Coruña',
      image: 'https://images.pexels.com/photos/1926769/pexels-photo-1926769.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'XL',
      status: 'Nuevo'
    },
    {
      id: 3,
      brand: 'Hoodie Cold Culture',
      name: 'Toledo',
      image: 'https://images.pexels.com/photos/3621436/pexels-photo-3621436.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'S',
      status: 'Acceptable'
    },
    {
      id: 4,
      brand: 'Camiseta EMEStudios',
      name: 'Valencia',
      image: 'https://images.pexels.com/photos/3622622/pexels-photo-3622622.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'L',
      status: 'Perfecto'
    },
    {
      id: 5,
      brand: 'Chaqueta LV Damier',
      name: 'A Coruña',
      image: 'https://images.pexels.com/photos/1926769/pexels-photo-1926769.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'XL',
      status: 'Nuevo'
    },
    {
      id: 6,
      brand: 'Hoodie Cold Culture',
      name: 'Toledo',
      image: 'https://images.pexels.com/photos/3621436/pexels-photo-3621436.jpeg?auto=compress&cs=tinysrgb&w=400',
      size: 'S',
      status: 'Acceptable'
    }
  ];

  savedProfiles: SavedProfile[] = [
    {
      id: 1,
      name: 'Yasikoki Tamoki',
      username: 'Sevilla',
      avatar: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=400',
      friends: 99,
      categories: ['camisetas', 'vaqueros', 'zapatos'],
      rating: 5
    },
    {
      id: 2,
      name: 'Yasikoki Tamoki',
      username: 'Sevilla',
      avatar: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=400',
      friends: 99,
      categories: ['camisetas', 'vaqueros', 'zapatos'],
      rating: 5
    },
    {
      id: 3,
      name: 'Yasikoki Tamoki',
      username: 'Sevilla',
      avatar: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=400',
      friends: 99,
      categories: ['camisetas', 'vaqueros', 'zapatos'],
      rating: 5
    },
    {
      id: 4,
      name: 'Yasikoki Tamoki',
      username: 'Sevilla',
      avatar: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=400',
      friends: 99,
      categories: ['camisetas', 'vaqueros', 'zapatos'],
      rating: 5
    },
    {
      id: 5,
      name: 'Yasikoki Tamoki',
      username: 'Sevilla',
      avatar: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=400',
      friends: 99,
      categories: ['camisetas', 'vaqueros', 'zapatos'],
      rating: 5
    },
    {
      id: 6,
      name: 'Yasikoki Tamoki',
      username: 'Sevilla',
      avatar: 'https://images.pexels.com/photos/220453/pexels-photo-220453.jpeg?auto=compress&cs=tinysrgb&w=400',
      friends: 99,
      categories: ['camisetas', 'vaqueros', 'zapatos'],
      rating: 5
    }
  ];

  changeTab(tab: string): void {
    this.activeTab = tab;
  }

  getStatusClass(status: string): string {
    return status.toLowerCase().replace(' ', '-');
  }

  generateStars(rating: number): number[] {
    return Array(rating).fill(0).map((_, i) => i + 1);
  }

}