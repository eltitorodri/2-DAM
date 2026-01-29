import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavegationBarComponent } from "../navegation-bar/navegation-bar.component";
import { SloganLogoComponent } from "../slogan-logo/slogan-logo.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-brand-selector',
  templateUrl: './brand-selector.component.html',
  styleUrls: ['./brand-selector.component.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, NavegationBarComponent, SloganLogoComponent],
})

export class BrandSelectorComponent {

  constructor(private router: Router) { }

  searchTerm = '';
  suggestion = '';

  brands = [
    { name: 'Nike', hidden: false },
    { name: 'Adidas', hidden: false },
    { name: 'Puma', hidden: false },
    { name: 'Ralph Lauren', hidden: false },
    { name: 'Tommy Hilfiger', hidden: false },
    { name: 'Lacoste', hidden: false },
    { name: 'Massimo Dutti', hidden: false },
    { name: 'Uniqlo', hidden: false },
    { name: 'Quiksilver', hidden: false },
    { name: 'Supreme', hidden: false },
    { name: 'Reebok', hidden: false },
    { name: 'Vans', hidden: false },
  ];

  navigateToBrand() : void {
    this.router.navigate(['/previousView'])
  }

  filteredBrands = [...this.brands];

  onSearch() {
    const term = this.searchTerm.toLowerCase();

    if (!term) {
      this.filteredBrands = this.brands.map(b => ({ ...b, hidden: false }));
      this.suggestion = '';
      return;
    }

    this.filteredBrands = this.brands.map(brand => ({
      ...brand,
      hidden: !brand.name.toLowerCase().includes(term)
    }));

    const firstMatch = this.brands.find(b =>
      b.name.toLowerCase().startsWith(term)
    );

    if (firstMatch && term.length > 0) {
      this.suggestion = firstMatch.name;
    } else {
      this.suggestion = '';
    }
  }
}
